package business;

import data.AccountDaoJPA;
import data.ProfileDaoJPA;
import domain.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;
import util.security.HashUtilMD5;

import javax.persistence.*;

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

        //clean the database off previous test contamination (different entityManager necessary)
        new DatabaseCleaner(emf.createEntityManager()).clean();

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

    @Test
    public void addAccountTest(){
        tx.begin();
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        tx.commit();
    }

    @After
    public void setDown() {
        em.close();
    }


    @Test
    public void getAccountByNameTest() {
        addAccountTest();
        tx.begin();
        Account account = accountService.getAccountByProfileName("peter");
        tx.commit();
        Assert.assertNotNull(account);
    }

    @Test(expected = RollbackException.class)
    public void nameCollisionTest(){
        addAccountTest();
        addAccountTest();
    }

    @Test
    public void loginTest(){
        addAccountTest();
        tx.begin();
        String token = accountService.login("peter@test.com", "peter");
        tx.commit();
        Assert.assertNotNull(token);
        Assert.assertFalse(token.equals(""));
    }


}
