package business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//@RunWith(Arquillian.class)
public class SubredditServiceTest {
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(SubredditService.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllSubreddits() {
        assertTrue(true);
    }

    @Test
    public void addSubreddit() {
        assertFalse(true);
    }

    @Test
    public void getSubredditByName() {
        assertTrue(true);
    }

    @Test
    public void getSubscriptionsForUser() {
        assertTrue(true);
    }

    @Test
    public void getModeratedSubs() {
        assertTrue(true);
    }

    @Test
    public void editSubreddit() {
        assertTrue(true);
    }
}
