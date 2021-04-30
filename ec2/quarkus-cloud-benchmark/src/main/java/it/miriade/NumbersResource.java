package it.miriade;

import io.smallrye.mutiny.Uni;
import it.miriade.dto.DivisorDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/numbers")
@Produces(MediaType.APPLICATION_JSON)
public class NumbersResource {

    @GET
    @Path("/{n}/divisors")
    public Uni<DivisorDTO> getDivisors(@PathParam("n") long n) {
        return Uni.createFrom().emitter(em -> {
            DivisorDTO divisor = new DivisorDTO();
            divisor.divisors = new ArrayList<>();

            for (long i = 1; i <= n; i++) {
                if (n % i == 0) {
                    divisor.divisors.add(i);
                }
            }
            em.complete(divisor);
        });
    }
}
