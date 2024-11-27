package roman.utils;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static roman.DriverProvider.getWait;

@Getter
public class MyElement {

    WebElement webElement;

    public MyElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public MyElement waitForVisible() {
        getWait().until(ExpectedConditions.visibilityOf(webElement));

        return this;
    }

    public boolean isVisible() {
        return webElement.isDisplayed();
    }
}
