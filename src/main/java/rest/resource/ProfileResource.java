package rest.resource;

import business.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/profiles")
@RequestScoped
public class ProfileResource {
    ProfileService profileService;

    @Inject
    public void setProfileService(ProfileService profileService){
        this.profileService = profileService;
    }
    @GET
    @Produces("application/json")
    public Response getProfiles(){
        //todo make method restful & all that juicy stuff
        return Response.status(Response.Status.OK).entity(profileService.getProfileList()).build();
    }
}
