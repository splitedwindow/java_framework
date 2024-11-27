package roman;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
    private static final ThreadLocal<WebDriver> DRIVER_INSTANCE = new ThreadLocal<>();

    private BrowserFactory() { }

    public static WebDriver getDriver() {
        if (DRIVER_INSTANCE.get() == null) {
            throw new RuntimeException("Please call .initBrowser(name) method before using");
        }
        return DRIVER_INSTANCE.get();
    }

    public static void initBrowser(String name) {
        // Initialize driver only if it's not already initialized
        if (DRIVER_INSTANCE.get() == null) {
            DriverProvider.initDriver(name);  // Initialize driver through DriverProvider
            DRIVER_INSTANCE.set(DriverProvider.getDriver());  // Set it in the ThreadLocal variable
        }
    }
}
