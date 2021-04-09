package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	r.GET("/go/greetings", func(c *gin.Context) {
		name := c.DefaultQuery("name", "world")
		c.JSON(200, gin.H{
			"message": fmt.Sprintf("Hello, %s!", name),
		})
	})
  r.Run(":8081") // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
