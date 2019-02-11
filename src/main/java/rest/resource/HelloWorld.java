package rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
public class HelloWorld {
    @GET
    @Produces("text/plain")
    public String hello(){
        return "hello from the rest.resource layer";
    }
}
