package roman.po;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import roman.DriverProvider;

public class RegisterPO {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement usernameElement;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordElement;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    private WebElement submitBtn;

    public RegisterPO() {
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }

    public RegisterPO openRegisterPage() {
        DriverProvider.getDriver().get("http://localhost:5173/register");

        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(usernameElement));
        return this;
    }

    public RegisterPO enterUsername(String username) {
        DriverProvider.getWait()
                .until(ExpectedConditions.visibilityOf(usernameElement));
        usernameElement.sendKeys(username);
        return this;
    }

    public RegisterPO enterPassword(String password) {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);
        return this;
    }

    public RegisterPO submit() {
        DriverProvider.getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//form//button[@type='submit']")));
        submitBtn.findElement(By.xpath("/html/body/div/main/form/button")).click();

        return this;
    }

    public boolean isDashboardVisible() {
        try {
            // Wait until the element is visible
            DriverProvider.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}



















