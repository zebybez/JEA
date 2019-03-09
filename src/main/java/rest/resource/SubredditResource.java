package rest.resource;

import business.interfaces.ProfileService;
import business.interfaces.SubredditService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/r")
public class SubredditResource {

    SubredditService subredditService;

    ProfileService profileService;

    @Inject
    public ProfileService getProfileService() {
        return profileService;
    }

    @Inject
    public void setSubredditService(SubredditService subredditService) {
        this.subredditService = subredditService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubreddits(){
        return Response.ok(subredditService.getAllSubreddits()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSubreddit(@HeaderParam("creatorId") long creatorId,
                                 @HeaderParam("name") String name) {
        return Response.ok(subredditService.addSubreddit(profileService.getProfileById(creatorId), name)).build();
    }
}
