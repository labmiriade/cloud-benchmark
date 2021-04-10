package main

import (
	"encoding/json"
  "fmt"
  "os"

  "github.com/aws/aws-sdk-go/aws"
  "github.com/aws/aws-sdk-go/service/dynamodb"
  "github.com/aws/aws-sdk-go/service/dynamodb/dynamodbattribute"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
)

type ErrorResponse struct {
	Message string `json:"message"`
}

var (
	sess, _ = session.NewSession(&aws.Config{})
  mainTable = os.Getenv("MainTable")
  dynamo = dynamodb.New(sess)
)

func HandleLambdaEvent(event events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	key, _ := event.PathParameters["key"]

  var response events.APIGatewayProxyResponse

  input := &dynamodb.GetItemInput{
    TableName: &mainTable,
    Key: map[string]*dynamodb.AttributeValue{
      "pk": {
        S: aws.String(key),
      },
      "sk": {
        S: aws.String(key),
      },
    },
  }
  res, err := dynamo.GetItem(input)
  var item interface{}

  if err == nil {
    err = dynamodbattribute.UnmarshalMap(res.Item, &item)
  }

  if err != nil || item == nil {
    body := map[string]string{"message": fmt.Sprintf("Item \"%s\" does not exist", key)}
    j, _ := json.Marshal(body)
    response = events.APIGatewayProxyResponse{
      StatusCode: 404,
      Body: string(j),
    }
  } else {
    j, _ := json.Marshal(item)
    response = events.APIGatewayProxyResponse{
      StatusCode: 200,
      Body: string(j),
    }
  }

  return response, nil
}

func main() {
	lambda.Start(HandleLambdaEvent)
}
