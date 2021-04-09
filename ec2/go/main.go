package main

import (
	"fmt"
	"strconv"

	"github.com/gin-gonic/gin"
)

func main() {
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
	r.Run(":8081") // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
