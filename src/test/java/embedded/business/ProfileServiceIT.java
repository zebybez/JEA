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
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.security.HashUtil;
import util.security.HashUtilMD5;
import util.security.Role;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class ProfileServiceIT {

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
//
//    @Deployment
//    public static WebArchive createFullProjectDeployment() {
//        return ShrinkWrap.create(EmbeddedGradleImporter.class)
//                .forThisProjectDirectory()
//                .importBuildOutput().as(WebArchive.class);
//    }

    @Inject
    private ProfileService profileService;

    @Inject
    private AccountService accountService;

//    @Inject
//    @OperateOnDeployment("full-project")
//    public void setAccountService(AccountService accountService){
//        this.accountService = accountService;
//    }

    @Before
    public void setup(){

    }

    @Test
    public void getProfileList(){
        accountService.addNewAccount("peter@test.com", "peter", "peter");
        accountService.addNewAccount("henk@test.com", "henk", "henk");
        profileService.getProfileList();
    }

    @Test
    public void getProfileByName() {

    }

    @Test
    public void getProfileById() {

    }

    @Test
    public void getSubscribersOfSubreddit() {

    }

    @Test
    public void getModeratorsOfSubreddit() {

    }
}
