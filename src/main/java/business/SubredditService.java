package business;

import data.interfaces.ProfileDao;
import data.interfaces.SubredditDao;
import domain.Profile;
import domain.Subreddit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Stateless
public class SubredditService implements business.interfaces.SubredditService {
    private SubredditDao subredditDao;
    private ProfileDao profileDao;

    @Inject
    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Context
    private SecurityContext securityContext;

    @Inject
    public void setSubredditDao(SubredditDao subredditDao) {
        this.subredditDao = subredditDao;
    }

    @Override
    public List<Subreddit> getAllSubreddits() {
        return subredditDao.getAll();
    }

    @Override
    public Subreddit addSubreddit(Profile profile, String name, String rules) {
        Subreddit subreddit = new Subreddit();
        subreddit.setName(name);
        subreddit.setCreator(profile);
        subreddit.setRules(rules);
        return subredditDao.persist(subreddit);
    }

    @Override
    public Subreddit getSubredditByName(String name){
        return subredditDao.findByName(name);
    }

    @Override
    public List<Subreddit> getSubscriptionsForUser(String userName) {
        return profileDao.getProfileByName(userName).getSubscriberOf();
    }

    @Override
    public List<Subreddit> getModeratedSubs(String userName) {
         return profileDao.getProfileByName(userName).getModeratorOf();
    }

    @Override
    public Subreddit editSubreddit(String name, String rules) {
        Subreddit subreddit = subredditDao.findByName(name);
        subreddit.setRules(rules);
        return subredditDao.persist(subreddit);
    }
}
