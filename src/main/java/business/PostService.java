package business;

import business.interfaces.ProfileService;
import business.interfaces.SubredditService;
import data.interfaces.PostDao;
import domain.Post;
import domain.Profile;
import domain.Subreddit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PostService implements business.interfaces.PostService {

    PostDao postDao;

    ProfileService profileService;

    SubredditService subredditService;

    @Inject
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Inject
    public void setProfileService(ProfileService profileService){
        this.profileService = profileService;
    }

    @Inject
    public void setSubredditService(SubredditService subredditService){
        this.subredditService = subredditService;
    }

    @Override
    public Post add(String username, String subredditName, String title, String content, boolean isNSFW) {
        Post post = new Post();
        Profile creator = profileService.getProfileByName(username);
        post.setCreator(creator);

        Subreddit subreddit = subredditService.getSubredditByName(subredditName);
        post.setSubreddit(subreddit);
        post.setTitle(title);
        post.setContent(content);
        post.setNSFW(isNSFW);
        return postDao.persist(post);
    }

    @Override
    public List<Post> getAll() {
        return postDao.getAll();
    }

    @Override
    public List<Post> getPostsByUser(String userName) {
        return profileService.getProfileByName(userName).getPosts();
    }

    @Override
    public List<Post> getPostsInSubreddit(String subreddit) {
        //return subredditService.getSubredditByName(subreddit).getPosts();
        return postDao.getAllInSubreddit(subreddit);
    }

    @Override
    public Post deletePost(long id) {
        Post post = postDao.find(id);
        postDao.delete(post);
        return post;
    }

    @Override
    public Post getPost(long id) {
        return postDao.find(id);
    }
}
