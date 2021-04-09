package it.miriade.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.miriade.lambda.dto.DivisorDTO;

import java.util.ArrayList;

public class NumbersLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        try {
            String enne = event.getPathParameters().get("n");
            long n = Long.parseLong(enne);

            DivisorDTO divisor = new DivisorDTO();
            divisor.divisors = new ArrayList<>();

            for (long i = 1; i <= n; i++) {
                if (n % i == 0) {
                    divisor.divisors.add(i);
                }
            }

            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setStatusCode(200);
            response.setIsBase64Encoded(false);
            response.setBody(OBJECT_MAPPER.writeValueAsString(divisor));
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
