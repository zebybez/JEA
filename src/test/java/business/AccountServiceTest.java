package business;

import data.interfaces.AccountDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void setUp(){
        accountService = new AccountService();
        AccountDao accountDaoMock = mock(AccountDao.class);

        accountService.setAccountDao(accountDaoMock);
    }

    @After
    public void setDown(){

    }

    @Test
    public void addNewAccount() {
    }

    @Test
    public void login() {
    }

    @Test
    public void getAllAccounts() {
    }
}
