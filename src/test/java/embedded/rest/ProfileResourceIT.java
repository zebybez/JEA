package embedded.rest;

import api.AuthenticationFilter;
import api.AuthorizationFilter;
import api.JAXRSConfig;
import api.resource.AccountResource;
import api.resource.ProfileResource;
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
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.Constants;
import util.DatabaseCleaner;
import util.security.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.when;

@RunWith(Arquillian.class)
public class ProfileResourceIT {
    @Inject
    private AccountService accountService;
    @Resource
    private UserTransaction utx;

    @Deployment
    public static WebArchive createProfileDeployment() {
        List<String> dependencies =new ArrayList<>();
        dependencies.add("io.jsonwebtoken:jjwt:0.9.1");
        dependencies.add("com.google.code.gson:gson:2.8.5");
        //todo add restassured as dependency for Arquillian
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
                .addClass(ProfileResource.class)
                .addClass(AccountResource.class)
                .addClass(AuthenticationFilter.class)
                .addClass(AuthorizationFilter.class)
                .addClass(JAXRSConfig.class)
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
    public void getSingle(){

    }

    @Test
    public void getList(){
       // when().get("/profiles"); //todo
    }

    @Test
    public void getSubscribersOfSubreddit(){

    }

    @Test
    public void getModeratorsOfSubreddit(){

    }
}
