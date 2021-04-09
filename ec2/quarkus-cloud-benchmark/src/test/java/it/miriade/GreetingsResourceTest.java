package it.miriade;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class GreetingsResourceTest {

    @Test
    void helloWorldTest() {
        given().when().get("/greetings").then().body("message", Matchers.equalTo("Hello, world!"));
    }

    @Test
    void helloJohnyTest() {
        given().when().get("/greetings?name=Johny").then().body("message", Matchers.equalTo("Hello, Johny!"));
    }
}
