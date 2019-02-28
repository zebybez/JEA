package business;

import data.ProfileDaoJPA;
import data.SubredditDaoJPA;
import domain.Profile;
import domain.Subreddit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SubredditService {
    private SubredditDaoJPA subredditDao;

    @Inject
    public void setSubredditDao(SubredditDaoJPA subredditDao) {
        this.subredditDao = subredditDao;
    }

    public List<Subreddit> getAllSubreddits() {
        return subredditDao.getAll();
    }

    public Subreddit addSubreddit(Profile profile, String name) {
        Subreddit subreddit = new Subreddit();
        subreddit.setName(name);
        subreddit.setCreator(profile);
        return subredditDao.persist(subreddit);
    }
}
