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
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.Constants;
import util.DatabaseCleaner;
import util.security.*;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Arquillian.class)
public class AccountAndProfileServiceIT {

    @Inject
    private ProfileService profileService;
    @Inject
    private AccountService accountService;
    @Resource
    private UserTransaction utx;

    @Deployment
    public static WebArchive createProfileDeployment() {
        List<String> dependencies =new ArrayList<>();
        dependencies.add("io.jsonwebtoken:jjwt:0.9.1");
        dependencies.add("com.google.code.gson:gson:2.8.5");
        File[] files = Maven.resolver().resolve(dependencies).withTransitivity().asFile();
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
                .addClass(Payload.class)
                .addClass(JWTHelper.class)
                .addClass(Constants.class)
                .addClass(RoleType.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(files);
    }

    @Before
    public void setup() {
        try {
            utx.begin();
            accountService.addNewAccount("peter@test.com", "peter", "peter");
            accountService.addNewAccount("henk@test.com", "henk", "henk");
            utx.commit();
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
    }

    @After
    public void getDown() {
        try {
            utx.begin();
            accountService.removeAccount(accountService.getAccountByProfileName("henk"));
            accountService.removeAccount(accountService.getAccountByProfileName("peter"));
            utx.commit();
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProfileList() {
        Assert.assertEquals(2, profileService.getProfileList().size());
    }

    @Test
    public void getProfileByName() {
        Profile profile = profileService.getProfileByName("henk");
        Assert.assertEquals("henk", profile.getName());
    }

    @Test
    public void getProfileById() {
        Profile profile = profileService.getProfileByName("henk");
        Assert.assertEquals(profile.getId(), profileService.getProfileById(profile.getId()).getId());
    }

    @Test
    public void getAccountByNameTest() {
        Account account = accountService.getAccountByProfileName("peter");
        Assert.assertNotNull(account);
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void nameCollisionTest() {
        accountService.addNewAccount("peter@test.com", "peter", "peter");
    }

    @Test
    public void loginTest() {
        String token = accountService.login("peter@test.com", "peter");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.equals(""));
    }

    @Test(expected = EJBException.class)
    public void loginFailTest() {
        String token = accountService.login("peter@test.com", "theWrongPassword");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.equals(""));
    }
}
