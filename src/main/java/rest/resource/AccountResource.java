package rest.resource;

import business.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {

    AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(){
        return Response.ok(accountService.getAllAccounts()).build();
    }
}
