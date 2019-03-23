package business;

import data.ProfileDaoJPA;
import data.SubredditDaoJPA;
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
    private ProfileService profileService;

    @Inject
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
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

    @Override
    public List<Subreddit> getSubscriptionsForUser(String userName) {
        return null;
    }

    @Override
    public List<Subreddit> getModeratedSubs(String userName) {
        return null;
    }

    @Override
    public Subreddit editSubreddit(String name, String rules, List<String> moderators) {
        return null;
    }
}
