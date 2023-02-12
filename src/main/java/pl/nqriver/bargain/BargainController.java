package pl.nqriver.bargain;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/bargains")
public class BargainController {

    @Inject
    BargainFacade bargainFacade;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<BargainResponse> create(BargainCreate bargainCreate) {
        return bargainFacade.create(bargainCreate);
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BargainMetersResponse> get(@RestPath Long id) {
        return bargainFacade.find(id);
    }
}