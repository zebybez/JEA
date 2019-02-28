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
        return null;
    }
}
