package api.resource;

import business.interfaces.ProfileService;
import business.interfaces.SubredditService;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/subreddits")
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

    @GET
    @Path("/{subreddit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubredditByName(@PathParam("subreddit") String subredditName){
        try{
            return Response.ok(subredditService.getSubredditByName(subredditName)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/sub/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriptions(@PathParam("userName") String userName){
        try{
            return Response.ok(subredditService.getSubscriptionsForUser(userName)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/mod/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModerated(@PathParam("userName") String userName){
        try{
            return Response.ok(subredditService.getModeratedSubs(userName)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editSubreddit(@HeaderParam("name") String name,
                                  @HeaderParam("rules") String rules,
                                  @HeaderParam("moderators") List<String> moderators) {
        return Response.ok(subredditService.editSubreddit(name, rules, moderators)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSubreddit(@HeaderParam("creatorId") long creatorId,
                                 @HeaderParam("name") String name,
                                 @HeaderParam("moderators") List<String> moderators) {
        return Response.ok(subredditService.addSubreddit(profileService.getProfileById(creatorId), name)).build();
    }


}
