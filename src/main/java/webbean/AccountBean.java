package webbean;

import business.interfaces.AccountService;
import domain.Account;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("accountBean")
@RequestScoped
public class AccountBean implements Serializable {

    private AccountService accountService;

    private String filter;

    private String email;
    private String name;
    private String password;

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        if (filter != null && filter.length() > 0) {
            ArrayList<Account> filtered = new ArrayList<>();
            for (Account s : accountService.getAllAccounts()) {
                if (s.getEmail().toLowerCase().contains(filter)) {
                    filtered.add(s);
                }
            }
            return filtered;
        } else {
            return accountService.getAllAccounts();
        }
    }

    public void addAccount(){
        accountService.addNewAccount(email, name, password);
    }

    public void removeAccount(Account account){
        accountService.removeAccount(account);
    }

    public void revokeAdmin(Account account){
        accountService.setRegular(account.getId());
    }

    public void makeAdmin(Account account){
        accountService.setAdmin(account.getId());
    }
}
