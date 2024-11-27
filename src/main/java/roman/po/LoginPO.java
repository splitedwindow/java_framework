package roman.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import roman.DriverProvider;

public class LoginPO {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement usernameElement;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordElement;

    @FindBy(xpath = "/html/body/div/main/form/button")
    private WebElement submitBtn;

    public LoginPO() {
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPO openLoginPage() {
        DriverProvider.getDriver().get("http://localhost:5173/login");

        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(usernameElement));
        return this;
    }


    // These are our bite-sized methods
    public LoginPO enterUsername(String username) {
        WebElement usernameInput = DriverProvider.getWait()
                .until(ExpectedConditions.visibilityOf(usernameElement));
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPO enterPassword(String password) {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);
        return this;
    }

    public void submit() {
        WebElement submitButton = DriverProvider.getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//form//button[@type='submit']")));
        submitBtn.findElement(By.xpath("/html/body/div/main/form/button")).click();
    }

    public void checkLogin() {
        boolean isLoggedIn = !driver.findElements(By.id("title")).isEmpty();

        Assert.assertTrue(isLoggedIn, "Login was not successful!");
    }

    public void performLogin(String username, String password) {
        openLoginPage();
        enterUsername(username);
        enterPassword(password);
        submit();
        checkLogin();


    }
}
