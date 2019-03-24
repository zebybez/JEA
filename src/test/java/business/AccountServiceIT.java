package business;

import data.AccountDaoJPA;
import data.ProfileDaoJPA;
import domain.Account;
import org.hibernate.exception.DataException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;
import util.security.HashUtilMD5;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceIT {
    private AccountService accountService;
    private ProfileService profileService;
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void setUp() {
        //setup the test database and transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(AccountServiceIT.class.getName()).log(Level.SEVERE, null, ex);
        }


        //setup the class to test (accountService)
        accountService = new AccountService();
        profileService = new ProfileService();

        ProfileDaoJPA profileDao = new ProfileDaoJPA();
        profileDao.setEntityManager(em);
        profileService.setProfileDao(profileDao);

        AccountDaoJPA accountDao = new AccountDaoJPA();
        accountDao.setEntityManager(em);
        accountService.setProfileService(profileService);
        accountService.setAccountDao(accountDao);
        accountService.setHashUtil(new HashUtilMD5());

    }

    private void addTestAccount(){
        tx.begin();
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        tx.commit();
    }

    @After
    public void setDown() {
        em.close();
    }


    @Test
    public void addNewAccount() {
        addTestAccount();
        tx.begin();
        Account account = accountService.getAccountByProfileName("peter");
        tx.commit();
        Assert.assertNotNull(account);
    }

    @Test(expected = RollbackException.class)
    public void nameCollisionTest(){
        addTestAccount();
        addTestAccount();
    }


}
