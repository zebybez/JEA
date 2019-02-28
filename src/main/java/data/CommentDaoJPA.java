package data;

import data.interfaces.CommentDao;
import domain.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDaoJPA extends BaseDaoJPA<Comment> implements CommentDao {
    public CommentDaoJPA() {
        super(Comment.class);
    }
}
