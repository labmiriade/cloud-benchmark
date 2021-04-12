package main

import (
	"encoding/json"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
	"strconv"
)

type MyEvent struct {
	Name string `json:"What is your name?"`
	Age  int    `json:"How old are you?"`
}

type MyResponse struct {
	Message string `json:"message"`
}

func HandleLambdaEvent(event events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	n, _ := strconv.Atoi(event.PathParameters["n"])

	var divisors []int
	for i := 1; i <= n; i++ {
		if n%i == 0 {
			divisors = append(divisors, i)
		}
	}

	m, _ := json.Marshal(divisors)
	response := events.APIGatewayProxyResponse{StatusCode: 200, Body: string(m)}
	return response, nil
}

func main() {
	lambda.Start(HandleLambdaEvent)
}
