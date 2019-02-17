package util;

import business.AccountService;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
@Startup
public class initData {
    @Inject
    private AccountService accountService;

    @PostConstruct
    public void createUsers(){
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        accountService.addNewAccount("henk@test.com", "henk", "henk");
    }
}
