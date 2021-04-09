package it.miriade;

import it.miriade.dto.MessageDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/greetings")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingsResource {

    @GET
    public MessageDTO greetings(@QueryParam("name") @DefaultValue("world") String name) {
        MessageDTO message = new MessageDTO();
        message.message = String.format("Hello, %s!", name);
        return message;
    }
}