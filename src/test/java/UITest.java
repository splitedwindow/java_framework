import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import roman.BrowserFactory;
import roman.bo.WebsiteBO;

public class UITest {
    @Test
    public void firefoxTest() {
        BrowserFactory.initBrowser("firefox");

        Faker faker = new Faker();
        String randomCredentials = faker.lorem().word();
        System.out.println("RANDOM CREDENTIAL: " + randomCredentials);

        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO
                .RegisterUser(randomCredentials, randomCredentials)
                .LoginUser(randomCredentials, randomCredentials);
    }

    @Test
    public void chromeTest() {
        BrowserFactory.initBrowser("chrome");


        WebsiteBO websiteBO = new WebsiteBO();
        websiteBO.LoginUser("test", "test");
    }

}
