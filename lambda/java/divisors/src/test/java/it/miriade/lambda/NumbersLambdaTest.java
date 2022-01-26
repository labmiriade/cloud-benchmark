package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.miriade.lambda.dto.DivisorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;

@QuarkusTest
class NumbersLambdaTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void tenDivisorTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.withPathParameters(Collections.singletonMap("n", "10"));

        APIGatewayProxyResponseEvent response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post()
                .thenReturn().as(APIGatewayProxyResponseEvent.class);

        Assertions.assertEquals(200, response.getStatusCode());
        try {
            DivisorDTO divisorDTO = OBJECT_MAPPER.readValue(response.getBody(), DivisorDTO.class);
            Assertions.assertNotNull(divisorDTO);
            Assertions.assertEquals(4, divisorDTO.divisors.size());
            Assertions.assertTrue(divisorDTO.divisors.containsAll(Arrays.asList(1L, 2L, 5L, 10L)));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void twentyDivisorTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.withPathParameters(Collections.singletonMap("n", "20"));

        APIGatewayProxyResponseEvent response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post()
                .thenReturn().as(APIGatewayProxyResponseEvent.class);

        Assertions.assertEquals(200, response.getStatusCode());
        try {
            DivisorDTO divisorDTO = OBJECT_MAPPER.readValue(response.getBody(), DivisorDTO.class);
            Assertions.assertNotNull(divisorDTO);
            Assertions.assertEquals(6, divisorDTO.divisors.size());
            Assertions.assertTrue(divisorDTO.divisors.containsAll(Arrays.asList(1L, 2L, 4L, 5L, 10L, 20L)));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

}
