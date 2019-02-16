package rest.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/auth")
@RequestScoped
public class AuthResource {

    @POST
    @Path("/login")
    public Response login(String username, String password){
        return Response.serverError().build();
    }
}
