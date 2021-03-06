package business.interfaces;

import domain.Account;

import java.util.List;

public interface AccountService {
    Account addNewAccount(String email, String name, String password);

    String login(String email, String password) throws SecurityException;

    List<Account> getAllAccounts();

    Account getAccountByProfileName(String name);

    Account setAdmin(long accountId);

    Account setRegular(long accountId);

    void removeAccount(Account account);
}
