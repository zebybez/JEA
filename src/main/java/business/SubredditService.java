package business;

import data.ProfileDaoJPA;
import data.SubredditDaoJPA;
import data.interfaces.SubredditDao;
import domain.Profile;
import domain.Subreddit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SubredditService implements business.interfaces.SubredditService {
    private SubredditDao subredditDao;

    @Inject
    public void setSubredditDao(SubredditDao subredditDao) {
        this.subredditDao = subredditDao;
    }

    @Override
    public List<Subreddit> getAllSubreddits() {
        return subredditDao.getAll();
    }

    @Override
    public Subreddit addSubreddit(Profile profile, String name) {
        Subreddit subreddit = new Subreddit();
        subreddit.setName(name);
        subreddit.setCreator(profile);
        return subredditDao.persist(subreddit);
    }

    @Override
    public Subreddit getSubredditByName(String name){
        return subredditDao.findByName(name);
    }
}
