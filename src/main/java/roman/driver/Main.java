package roman.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Main {
    public static void main(String[] args) {
        // Set paths to the WebDriver executables
        System.setProperty("webdriver.chrome.roman.driver", "path/to/chromedriver");
        System.setProperty("webdriver.gecko.roman.driver", "path/to/geckodriver");

        // Initialize the DriverPool
        DriverPool pool = new DriverPool();

        // Add browser instances to the pool
        pool.addBrowser(new ChromeDriver());
        pool.addBrowser(new SafariDriver());

        // Launch all browsers with the specified URL
        pool.launchAll("http://localhost:5173/");
    }
}
