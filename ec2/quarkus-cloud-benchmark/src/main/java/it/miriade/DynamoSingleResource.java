package it.miriade;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("dynamo/single/{key}")
@Produces(MediaType.APPLICATION_JSON)
public class DynamoSingleResource {

    @ConfigProperty(name = "it.miriade.dynamodb.table")
    String tableName;

    @Inject
    DynamoDbClient dynamoDB;

    @GET
    public Response dySingle(@PathParam("key") String key) {
        final Map<String, AttributeValue> keyMap = new HashMap<>(2);
        keyMap.put("pk", AttributeValue.builder().s(key).build());
        keyMap.put("sk", AttributeValue.builder().s(key).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(keyMap)
                .build();

        Map<String, AttributeValue> item = dynamoDB.getItem(request).item();

        if (item.isEmpty()) {
            Map<String, String> entity = new HashMap<>(1);
            entity.put("message", String.format("Item \"%s\" does not exist", key));
            return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
        }

        Map<String, String> response = new HashMap<>(item.size());
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            response.put(entry.getKey(), entry.getValue().s());
        }

        return Response.status(Response.Status.OK).entity(response).build();
    }
}
