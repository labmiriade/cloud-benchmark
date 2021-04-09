package it.miriade;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@QuarkusTest
class NumbersResourceTest {

    @Test
    void tenDivisorTest() {
        given().when().get("/numbers/10/divisors").then()
                .body("divisors.size", Matchers.equalTo(4))
                .body("divisors", Matchers.containsInAnyOrder(1, 2, 5, 10));
    }

    @Test
    void twentyDivisorTest() {
        given().when().get("/numbers/20/divisors").then()
                .body("divisors.size", Matchers.equalTo(6))
                .body("divisors", Matchers.containsInAnyOrder(1, 2, 4, 5, 10, 20));
    }

}
