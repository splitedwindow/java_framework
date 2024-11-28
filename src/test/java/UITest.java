import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.*;
import roman.BrowserFactory;
import roman.bo.WebsiteBO;
import roman.models.User;
import roman.utils.AllureUtils;
import roman.utils.HibernateConnector;

import static roman.DriverProvider.getDriver;

@Listeners({TestListener.class})
public class UITest {
    @DataProvider
    public Object[][] testCredentialsProvider() {
        return new Object[][] {
                {"test", "test"}
        };
    }

    @DataProvider
    public Object[][] userRandomCredentialsProvider() {
        Faker faker = new Faker();
        String randomCredentials = faker.lorem().word();
        return new Object[][] {
                {randomCredentials, randomCredentials}
        };
    }


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

        // test in db
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();

        String emailToFind = randomCredentials;
        User user = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", emailToFind)
                .uniqueResult();

        Assert.assertEquals(emailToFind, user.getEmail(), "emails don't match");

        session.getTransaction().commit();

        session.close();
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

    @Parameters({"username", "password"})
    @Test(dataProvider = "testCredentialsProvider")
    public void firefoxLoginTest(String username, String password) {
        BrowserFactory.initBrowser("firefox");

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.loginUser(username, password);

        Assert.assertTrue(websiteBO.isLoginSuccessful(), "Dashboard is not visible; login failed.");

        // validate via hibernate
        Session session = HibernateConnector.getSessionFactory().openSession();

        String emailToFind = "test";
        User user = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", emailToFind)
                .uniqueResult();

        Assert.assertEquals("test", user.getEmail(), "emails don't match");

        session.close();
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


    @Parameters({"username", "password"})
    @Test(dataProvider="userRandomCredentialsProvider")
    public void registerTest(String username, String password) {
        BrowserFactory.initBrowser("firefox");

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.registerUser(username, password);

        Assert.assertTrue(websiteBO.isRegisterSuccessful(), "Dashboard is not visible; login failed.");

        // test in db
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();

        String emailToFind = username;
        User user = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", emailToFind)
                .uniqueResult();

        Assert.assertEquals(emailToFind, user.getEmail(), "emails don't match");

        session.getTransaction().commit();

        session.close();

        AllureUtils.attachScreenshot(getDriver());
        AllureUtils.attachHtml(getDriver());
    }


    @BeforeSuite
    public void initDriver() {
        System.out.println("Initializing driver...");
        BrowserFactory.initBrowser("chrome");
    }

//    @AfterSuite
//    public void closeDriver() {
//        DriverProvider.getDriver().quit();
//    }
}
