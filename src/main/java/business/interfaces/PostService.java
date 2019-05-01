package business.interfaces;

import domain.Post;

import java.util.List;

public interface PostService {
    Post add(String username, String subreddit, String title, String content, boolean isNSFW);

    List<Post> getAll();

    List<Post> getPostsByUser(String userName);

    List<Post> getPostsInSubreddit(String subreddit);

    Post deletePost(long id);

    Post getPost(long id);
}
