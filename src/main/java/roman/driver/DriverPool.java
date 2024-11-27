package roman.driver;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DriverPool {
    private final List<WebDriver> browsers = new ArrayList<>();

    // Add a browser instance to the pool
    public void addBrowser(WebDriver driver) {
        browsers.add(driver);
    }

    // Launch all browsers in parallel
    public void launchAll(String url) {
        ExecutorService executor = Executors.newFixedThreadPool(browsers.size());

        for (WebDriver browser : browsers) {
            executor.submit(() -> {
                try {
                    browser.get(url);
                    System.out.println(browser.getClass().getSimpleName() + ": Page title is '" + browser.getTitle() + "'");
                } finally {
                    browser.quit();
                }
            });
        }

        executor.shutdown(); // Gracefully shut down the executor after tasks are complete
        while (!executor.isTerminated()) {
            // Wait for all tasks to complete
        }
    }
}
