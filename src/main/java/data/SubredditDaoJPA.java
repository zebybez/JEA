package data;

import domain.Profile;
import domain.Subreddit;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SubredditDaoJPA extends BaseDaoJPA<Subreddit> {


    public SubredditDaoJPA() {
        super(Subreddit.class);
    }

    public List<Subreddit> getAll() {
        return getEm().createQuery("SELECT p FROM Subreddit p", Subreddit.class)
                .getResultList();
    }
}
