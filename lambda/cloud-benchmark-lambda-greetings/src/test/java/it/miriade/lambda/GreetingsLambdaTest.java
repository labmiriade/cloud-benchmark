package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import it.miriade.lambda.dto.MessageDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

@QuarkusTest
class GreetingsLambdaTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void helloWorldTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        APIGatewayProxyResponseEvent response = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
        Assertions.assertEquals(200, response.getStatusCode());
        try {
            MessageDTO messageDTO = OBJECT_MAPPER.readValue(response.getBody(), MessageDTO.class);
            Assertions.assertNotNull(messageDTO);
            Assertions.assertEquals("Hello, world!", messageDTO.message);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void helloJohnyTest() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.withQueryStringParameters(Collections.singletonMap("name", "Johny"));
        APIGatewayProxyResponseEvent response = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, request);
        Assertions.assertEquals(200, response.getStatusCode());
        try {
            MessageDTO messageDTO = OBJECT_MAPPER.readValue(response.getBody(), MessageDTO.class);
            Assertions.assertNotNull(messageDTO);
            Assertions.assertEquals("Hello, Johny!", messageDTO.message);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
