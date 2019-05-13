package util;

import business.interfaces.PostService;
import business.interfaces.AccountService;
import business.interfaces.ProfileService;
import business.interfaces.SubredditService;
import domain.Account;

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

    @Inject
    private PostService postService;

    @PostConstruct
    public void initialize(){
        initUsers();
        initSubreddits();
        initPosts();
        initComments();
    }

    private void initUsers(){
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        accountService.addNewAccount("henk@test.com", "henk", "henk");
        accountService.addNewAccount("admin@test.com", "admin", "admin");

        //make the admin account an admin
        Account admin = accountService.getAccountByProfileName("admin");
        accountService.setAdmin(admin.getId());
    }

    private void initSubreddits(){
        subredditService.addSubreddit(profileService.getProfileByName("henk"), "henkssub", "just have fun, LOL");
    }

    private void initPosts(){
        postService.add("peter", "henkssub", "heyHenk", "ik heb een post gedaan in je sub", false);
    }

    private void initComments(){}
}
