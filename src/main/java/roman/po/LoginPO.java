package roman.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        return this;
    }


    // These are our bite-sized methods
    public LoginPO enterUsername(String username) {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(passwordElement));
        usernameElement.sendKeys(username);
        return this;
    }

    public LoginPO enterPassword(String password) {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);
        return this;
    }

    public LoginPO submit() {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(submitBtn));
        submitBtn.click();

        return this;
    }

}
