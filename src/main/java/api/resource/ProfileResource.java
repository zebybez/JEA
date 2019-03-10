package api.resource;

import business.interfaces.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profiles")
@RequestScoped
public class ProfileResource {
    business.interfaces.ProfileService profileService;

    @Inject
    public void setProfileService(ProfileService profileService){
        this.profileService = profileService;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("name") String name){
        //todo make method restful & all that juicy stuff
        return Response.ok(profileService.getProfileByName(name)).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(){
        //todo make method restful & all that juicy stuff
        return Response.status(Response.Status.OK).entity(profileService.getProfileList()).build();
    }

    public Response put() {
        return null;
    }

    public Response delete() {
        return null;
    }
}
