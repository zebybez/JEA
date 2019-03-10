package api.resource;

import business.interfaces.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@RequestScoped
public class AuthResource {

    private AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@HeaderParam("email") String email,
                          @HeaderParam("password") String password) {
        try {
            String jws = accountService.login(email, password);
            return Response.ok(jws).build();
        } catch (SecurityException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("email") String email,
                             @HeaderParam("name") String name,
                             @HeaderParam("password") String password) {
        return Response.ok(accountService.addNewAccount(email, name, password)).build();
    }


}
