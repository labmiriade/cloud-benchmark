package main

import (
	"fmt"
	"os"
	"strconv"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/dynamodb"
	"github.com/aws/aws-sdk-go/service/dynamodb/dynamodbattribute"
	"github.com/gin-gonic/gin"
)

var (
	sess, _   = session.NewSession(&aws.Config{})
	mainTable = os.Getenv("MainTable")
	dynamo    = dynamodb.New(sess)
)

const Usage = "Vanno impostate le variabili `MainTable` e `AWS_REGION` per il corretto funzionamento dell'applicativo"

func main() {

  if mainTable == "" || os.Getenv("AWS_REGION") == "" {
    fmt.Println(Usage)
  }

	r := gin.Default()
	r.GET("/greetings", func(c *gin.Context) {
		name := c.DefaultQuery("name", "world")
		c.JSON(200, gin.H{
			"message": fmt.Sprintf("Hello, %s!", name),
		})
	})
	r.GET("/numbers/:n/divisors", func(c *gin.Context) {
		nstr := c.Param("n")
		n, _ := strconv.Atoi(nstr)

		var aux []int

		for i := 1; i <= n; i++ {
			if n%i == 0 {
				aux = append(aux, i)
			}
		}

		c.JSON(200, aux)
	})
	r.GET("/dynamo/single/:key", func(c *gin.Context) {
		key := c.Param("key")

		var response interface{}
		var code int

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
    } else {
      fmt.Println(err)
    }
		if err != nil || item == nil {
			response = map[string]string{"message": fmt.Sprintf("Item \"%s\" does not exist", key)}
			code = 404
		} else {
			response = item
			code = 200
		}

		c.JSON(code, response)
	})
	r.Run(":8081") // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
