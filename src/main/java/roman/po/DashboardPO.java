package roman.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import roman.DriverProvider;

public class DashboardPO {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"title\"]")
    private WebElement dashboardTitle;

    @FindBy(xpath = "/html/body/div[4]/main/form/input")
    private WebElement chooseFileBtn;

    @FindBy(xpath = "/html/body/div[4]/main/form/button")
    private WebElement uploadImgBtn;

    @FindBy(xpath = "/html/body/div[4]/main/form/div/button")
    private WebElement addNewElemBtn;

    @FindBy(xpath = "/html/body/div[4]/div[1]/input")
    private WebElement getDataInput;

    @FindBy(xpath = "//*[@id=\"getDataBtn\"]")
    private WebElement getDataBtn;

    public DashboardPO() {
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }

    public DashboardPO openDashboardPage() {
        DriverProvider.getDriver().get("http://localhost:5173/dashboard");

        return this;
    }


}
