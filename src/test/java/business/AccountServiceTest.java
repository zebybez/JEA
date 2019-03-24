package business;

import data.AccountDaoJPA;
import data.ProfileDaoJPA;
import domain.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.security.HashUtilMD5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AccountServiceTest {
    private AccountService accountService;
    private ProfileService profileService;
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
        tx = em.getTransaction();
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

    @After
    public void setDown() {

    }

    @Test
    public void addNewAccount() {
        tx.begin();
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        tx.commit();
        tx.begin();
        Account account = accountService.getAccountByProfileName("peter");
        tx.commit();
        Assert.assertNotNull(account);
    }


}
