package data;

import domain.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDaoJPA extends BaseDaoJPA<Comment> {
    public CommentDaoJPA() {
        super(Comment.class);
    }
}
