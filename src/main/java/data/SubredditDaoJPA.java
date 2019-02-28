package data;

import data.interfaces.SubredditDao;
import domain.Subreddit;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SubredditDaoJPA extends BaseDaoJPA<Subreddit> implements SubredditDao {


    public SubredditDaoJPA() {
        super(Subreddit.class);
    }

    @Override
    public List<Subreddit> getAll() {
        return getEm().createQuery("SELECT p FROM Subreddit p", Subreddit.class)
                .getResultList();
    }
}
