package roman.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import roman.DriverProvider;

public class RegisterPO {
    private final WebDriver driver;

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
        WebElement usernameInput = DriverProvider.getWait()
                .until(ExpectedConditions.visibilityOf(usernameElement));
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public RegisterPO enterPassword(String password) {
        DriverProvider.getWait().until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);
        return this;
    }

    public void submit() {
        WebElement submitButton = DriverProvider.getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//form//button[@type='submit']")));
        submitBtn.findElement(By.xpath("/html/body/div/main/form/button")).click();
    }

    public void performRegister(String username, String password) {
        openRegisterPage();
        enterUsername(username);
        enterPassword(password);
        submit();
    }
}



















