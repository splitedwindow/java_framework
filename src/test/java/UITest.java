import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import roman.BrowserFactory;
import roman.bo.WebsiteBO;
import roman.models.User;
import roman.utils.HibernateConnector;

public class UITest {
    @Test
    public void firefoxRegisterLoginTest() {
        BrowserFactory.initBrowser("firefox");

        Faker faker = new Faker();
        String randomCredentials = faker.lorem().word();
        System.out.println("RANDOM CREDENTIAL: " + randomCredentials);

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO
                .registerUser(randomCredentials, randomCredentials)
                .loginUser(randomCredentials, randomCredentials);

        Assert.assertTrue(websiteBO.isLoginSuccessful(), "Dashboard is not visible; login failed.");
    }

    @Test
    public void chromeRegisterLoginTest() {
        BrowserFactory.initBrowser("chrome");

        Faker faker = new Faker();
        String randomCredentials = faker.lorem().word();
        System.out.println("RANDOM CREDENTIAL: " + randomCredentials);

        // register -> login
        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO
                .registerUser(randomCredentials, randomCredentials)
                .loginUser(randomCredentials, randomCredentials);

        Assert.assertTrue(websiteBO.isLoginSuccessful(), "Dashboard is not visible; login failed.");
    }

    @Test
    public void firefoxLoginTest() {
        BrowserFactory.initBrowser("firefox");

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.loginUser("test", "test");

        Assert.assertTrue(websiteBO.isLoginSuccessful(), "Dashboard is not visible; login failed.");

        // validate via hibernate
//        Session session = HibernateConnector.getSessionFactory().openSession();
    }

    @Test
    public void testDbConnectionTest() {
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();  // Start the transaction

        // Create and persist a new user
        User user = new User("aAaA", "aAaA", "aAaA");

        // Save the user
//        session.save(user);

        // Retrieve the user
        User receivedUser = session.get(User.class, 1);  // Get by userId
        System.out.println(receivedUser.getEmail());

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }



    @Test
    public void registerTest() {
        BrowserFactory.initBrowser("firefox");

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.loginUser("test", "test");

        Assert.assertTrue(websiteBO.isRegisterSuccessful(), "Dashboard is not visible; login failed.");
    }
}
