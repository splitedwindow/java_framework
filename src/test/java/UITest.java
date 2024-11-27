import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import roman.BrowserFactory;
import roman.bo.WebsiteBO;

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
    }

    @Test
    public void registerTest() {
        BrowserFactory.initBrowser("firefox");

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.loginUser("test", "test");

        Assert.assertTrue(websiteBO.isRegisterSuccessful(), "Dashboard is not visible; login failed.");
    }
}
