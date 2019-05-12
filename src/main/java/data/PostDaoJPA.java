package data;

import data.interfaces.PostDao;
import domain.Post;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PostDaoJPA extends BaseDaoJPA<Post> implements PostDao {
    public PostDaoJPA() {
        super(Post.class);
    }

    @Override
    public List<Post> getAll() {
        return getEm().createQuery("SELECT p FROM Post p", Post.class)
                .getResultList();
    }

    @Override
    public List<Post> getAllInSubreddit(String subreddit) {
        return getEm().createQuery("SELECT p FROM Post p WHERE p.subreddit.name = :subreddit", Post.class)
                .setParameter("subreddit", subreddit)
                .getResultList();
    }
}
