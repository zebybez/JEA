package data.interfaces;

import domain.Post;

import java.util.List;

public interface PostDao extends BaseDao <Post> {
    List<Post> getAll();

    List<Post> getAllInSubreddit(String subreddit);
}
