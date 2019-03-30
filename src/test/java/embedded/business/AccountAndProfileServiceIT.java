package embedded.business;


import business.interfaces.AccountService;
import business.interfaces.ProfileService;
import data.AccountDaoJPA;
import data.BaseDaoJPA;
import data.ProfileDaoJPA;
import data.interfaces.AccountDao;
import data.interfaces.BaseDao;
import data.interfaces.ProfileDao;
import domain.*;
import domain.interfaces.Judgeable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.DatabaseCleaner;
import util.security.HashUtil;
import util.security.HashUtilMD5;
import util.security.Role;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

@RunWith(Arquillian.class)
public class AccountAndProfileServiceIT {

    @Deployment
    public static WebArchive createProfileDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(Profile.class)
                .addClass(Account.class)
                .addClass(Post.class)
                .addClass(Comment.class)
                .addClass(Subreddit.class)
                .addClass(Judgeable.class)
                .addClass(BaseDao.class)
                .addClass(HashUtil.class)
                .addClass(HashUtilMD5.class)
                .addClass(Role.class)
                .addClass(DatabaseCleaner.class)
                .addClass(BaseDaoJPA.class)
                .addClass(ProfileService.class)
                .addClass(business.ProfileService.class)
                .addClass(ProfileDao.class)
                .addClass(ProfileDaoJPA.class)
                .addClass(AccountService.class)
                .addClass(business.AccountService.class)
                .addClass(AccountDao.class)
                .addClass(AccountDaoJPA.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfileService profileService;

    @Inject
    private AccountService accountService;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @Before
    public void setup(){
        try {
            utx.begin();
            accountService.addNewAccount("peter@test.com", "peter", "peter");
            accountService.addNewAccount("henk@test.com", "henk", "henk");
            utx.commit();
//            utx.begin();
//            em.joinTransaction();
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
    }

    @After
    public void getdown(){
        try {
//            utx.commit();
            utx.begin();
            accountService.removeAccount(accountService.getAccountByProfileName("henk"));
            accountService.removeAccount(accountService.getAccountByProfileName("peter"));
            utx.commit();
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProfileList(){
        Assert.assertEquals(2, profileService.getProfileList().size());
    }

    @Test
    public void getProfileByName() {
        Profile profile = profileService.getProfileByName("henk");
        Assert.assertEquals("henk", profile.getName());
    }

    @Test
    public void getProfileById() {
        Profile profile = profileService.getProfileById(3);
        Assert.assertNotNull(profile);
    }

    @Test
    public void getAccountByNameTest() {
        Account account = accountService.getAccountByProfileName("peter");
        Assert.assertNotNull(account);
    }

    @Test(expected = javax.persistence.RollbackException.class)
    public void nameCollisionTest(){
        accountService.addNewAccount("peter@test.com", "peter", "peter");
    }

    @Test
    public void loginTest(){
        String token = accountService.login("peter@test.com", "peter");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.equals(""));
    }

    @Test(expected = SecurityException.class)
    public void loginFailTest(){
        String token = accountService.login("peter@test.com", "theWrongPassword");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.equals(""));
    }
}