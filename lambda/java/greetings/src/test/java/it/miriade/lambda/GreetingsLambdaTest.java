package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Collections;

@QuarkusTest
class GreetingsLambdaTest {

    @Test
    void helloWorldTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("body", containsString("Hello, world!"));
    }

    @Test
    void helloJohnyTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.withQueryStringParameters(Collections.singletonMap("name", "Johny"));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("body", containsString("Hello, Johny!"));
    }
}
