package rest.resource;

import business.AccountService;
import util.security.Payload;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/auth")
@RequestScoped
public class AuthResource {

    private AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

    @POST
    @Path("/login")
    public Response login(@HeaderParam("email") String email,
                          @HeaderParam("password") String password){
        Payload payload = accountService.
        return Response.serverError().build();
    }

    @POST
    @Path("/register")
    public Response register(@HeaderParam("email") String email,
                             @HeaderParam("name") String name,
                             @HeaderParam("password") String password){
        return null;
    }


}
