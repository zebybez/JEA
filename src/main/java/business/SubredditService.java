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
    SubredditDaoJPA subredditDao;
    ProfileDaoJPA profileDao;

    @Inject
    public void setSubredditDao(SubredditDaoJPA subredditDao) {
        this.subredditDao = subredditDao;
    }

    @Inject
    public void setProfileDao(ProfileDaoJPA profileDao) {
        this.profileDao = profileDao;
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
