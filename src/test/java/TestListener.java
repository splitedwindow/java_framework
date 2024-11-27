import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static roman.DriverProvider.getDriver;

public class TestListener implements ITestListener, IInvokedMethodListener {
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.afterInvocation(method, testResult);
        makeDOMAttachment();
        makeScreenShot();
    }

    @Attachment(value="Page screenshot", type="image/png")
    private byte[] makeScreenShot() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // To avoid silent failures
        }
    }

    @Attachment(value="DOM", type="text/plain")
    private String makeDOMAttachment() {
        return getDriver().getPageSource();
    }
}
