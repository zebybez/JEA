package api.resource;

import business.interfaces.ProfileService;
import util.annotations.Secured;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/profiles")
@RequestScoped
public class ProfileResource {
    business.interfaces.ProfileService profileService;

    @Inject
    public void setProfileService(ProfileService profileService){
        this.profileService = profileService;
    }

    @Context
    SecurityContext securityContext;

    @Secured
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("name") String name){
        //todo make method restful & all that juicy stuff
        if(securityContext.getUserPrincipal() != null && securityContext.getUserPrincipal().getName().equals(name)) {
            return Response.ok(profileService.getProfileByName(name)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(){
        //todo make method restful & all that juicy stuff
        //securityContext.getUserPrincipal().getName();
        return Response.status(Response.Status.OK).entity(profileService.getProfileList()).build();
    }

    @GET
    @Path("/subs/{subreddit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscribersOfSubreddit(@PathParam("subreddit") String subreddit){
        try{
            return Response.ok(profileService.getSubscribersOfSubreddit(subreddit)).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/mods/{subreddit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModeratorsOfSubreddit(@PathParam("subreddit") String subreddit){
        try{
            return Response.ok(profileService.getModeratorsOfSubreddit(subreddit)).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response put() {
        return null;
    }

    public Response delete() {
        return null;
    }
}
