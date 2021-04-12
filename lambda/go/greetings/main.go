package main

import (
	"encoding/json"
	"fmt"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
)

type MyEvent struct {
	Name string `json:"What is your name?"`
	Age  int    `json:"How old are you?"`
}

type MyResponse struct {
	Message string `json:"message"`
}

func HandleLambdaEvent(event events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	name, found := event.QueryStringParameters["name"]
	if !found {
		name = "world"
	}
	message := &MyResponse{Message: fmt.Sprintf("Hello, %s!", name)}
	m, _ := json.Marshal(message)
	response := events.APIGatewayProxyResponse{StatusCode: 200, Body: string(m)}
	return response, nil
}

func main() {
	lambda.Start(HandleLambdaEvent)
}
