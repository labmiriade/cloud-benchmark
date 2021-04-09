package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.miriade.lambda.dto.DivisorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;

import java.util.Arrays;
import java.util.Collections;

@QuarkusTest
class NumbersLambdaTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void tenDivisorTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.withPathParameters(Collections.singletonMap("n", "10"));
        APIGatewayProxyResponseEvent response = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
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
        APIGatewayProxyResponseEvent response = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
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
