package api.resource;

import business.interfaces.PostService;
import util.annotations.Secured;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@Path("posts")
public class PostResource {

    PostService postService;

    @Context
    SecurityContext securityContext;

    @Inject
    public void setPostService(PostService postService){
        this.postService = postService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(){
        return Response.ok(postService.getAll()).build();
    }

    @Secured
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@HeaderParam("subreddit") String subreddit,
                        @HeaderParam("title") String title,
                        @HeaderParam("content") String content,
                        @HeaderParam("isNSFW") boolean isNSFW){
        String username = securityContext.getUserPrincipal().getName();
        return Response.ok(postService.add(username, subreddit, title, content, isNSFW)).build();
    }
}
