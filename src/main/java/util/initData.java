package util;

import business.AccountService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
public class initData {
    private AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

    @PostConstruct
    public void createUsers(){
        accountService.addNewAccount("peter@test.com", "peter");
        accountService.addNewAccount("henk@test.com", "henk");
    }
}
