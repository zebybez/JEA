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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("id") long id){
        try{
            return Response.ok(postService.getPost(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/sub/{subreddit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostsInSubreddit(@PathParam("subreddit") String subreddit){
        try{
            return Response.ok(postService.getPostsInSubreddit(subreddit)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Secured
    @GET
    @Path("/user/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostsByUser(@PathParam("userName") String userName){
        try{
            return Response.ok(postService.getPostsByUser(userName)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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

    @Secured
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){
        try{
            return Response.ok(postService.deletePost(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
