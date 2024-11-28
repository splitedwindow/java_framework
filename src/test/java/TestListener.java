import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static roman.DriverProvider.getDriver;

public class TestListener implements ITestListener, IInvokedMethodListener {
    protected final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        LOGGER.info("Test Started: " + result.getMethod().getMethodName() + " " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        LOGGER.info("Test Started: " + result.getMethod().getMethodName() + " " + result.getMethod().getDescription());
        makeScreenShot();
        makeDOMAttachment();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        LOGGER.info("Test Started: " + result.getMethod().getMethodName() + " " + result.getMethod().getDescription());
        makeScreenShot();
        makeDOMAttachment();
    }

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
        LOGGER.error("makeDOMAttachment");
        return getDriver().getPageSource();
    }

}
