package util;

import business.AccountService;
import business.ProfileService;
import business.SubredditService;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
@Startup
public class initData {
    @Inject
    private AccountService accountService;

    @Inject
    private ProfileService profileService;

    @Inject
    private SubredditService subredditService;

    @PostConstruct
    public void createUsers(){
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        accountService.addNewAccount("henk@test.com", "henk", "henk");

        subredditService.addSubreddit(profileService.getProfileByName("henk"), "henkssub");
    }
}
