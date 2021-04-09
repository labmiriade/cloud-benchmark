package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.miriade.lambda.dto.MessageDTO;

import java.util.Map;
import java.util.Optional;

public class GreetingsLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        String name = "world";
        Optional<Map<String, String>> queryParams = Optional.ofNullable(event.getQueryStringParameters());
        if (queryParams.isPresent() && queryParams.get().containsKey("name")) {
            name = queryParams.get().get("name");
        }
        MessageDTO message = new MessageDTO();
        message.message = String.format("Hello, %s!", name);
        try {
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setStatusCode(200);
            response.setIsBase64Encoded(false);
            response.setBody(OBJECT_MAPPER.writeValueAsString(message));
            return response;
        } catch (Exception e) {
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setStatusCode(500);
            response.setIsBase64Encoded(false);
            response.setBody(e.getMessage());
            return response;
        }
    }
}
