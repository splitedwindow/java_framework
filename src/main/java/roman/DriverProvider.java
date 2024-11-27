package roman;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverProvider {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDriverWait wait;

    private DriverProvider() {
        // Private constructor to prevent instantiation
    }

    private static void isDriverInitialized() {
        if (driver.get() == null) {
            throw new RuntimeException("DRIVER HAS NOT BEEN INITIALIZED");
        }
    }

    public static WebDriverWait getWait() {
        isDriverInitialized();

        if (wait == null) {
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(3));
        }
        return wait;
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Please call .initBrowser(name) method before using");
        }
        return driver.get();
    }

    // Method to initialize the driver
    public static void initDriver(String name) {
        if (driver.get() != null) {
            return; // Already initialized
        }
        switch (name) {
            case "chrome" -> {
                ChromeDriverManager.getInstance().setup();
                driver.set(new ChromeDriver());
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }
            default -> throw new RuntimeException("Invalid browser " + name);
        }
    }
}
