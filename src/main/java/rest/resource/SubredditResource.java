package rest.resource;

import business.ProfileService;
import business.SubredditService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response getSubreddits(){
        return Response.ok(subredditService.getAllSubreddits()).build();
    }

    @POST
    public Response addSubreddit(@HeaderParam("creatorId") long creatorId,
                                 @HeaderParam("name") String name) {
        return Response.ok(subredditService.addSubreddit(profileService.getProfileById(creatorId), name)).build();
    }
}
