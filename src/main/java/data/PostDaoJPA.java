package data;

import domain.Post;

import javax.ejb.Stateless;

@Stateless
public class PostDaoJPA extends BaseDaoJPA<Post> {
    public PostDaoJPA() {
        super(Post.class);
    }
}
