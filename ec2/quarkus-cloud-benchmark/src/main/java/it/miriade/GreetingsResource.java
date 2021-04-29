package it.miriade;

import io.smallrye.mutiny.Uni;
import it.miriade.dto.MessageDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/greetings")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingsResource {

    @GET
    public Uni<MessageDTO> greetings(@QueryParam("name") @DefaultValue("world") String name) {
        MessageDTO message = new MessageDTO();
        message.message = String.format("Hello, %s!", name);
        return Uni.createFrom().item(message);
    }
}