package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static WebDriver configureWebDriver() {
        if (isWindows()) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win.exe");
        } else if (isUnix()) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_lin");
        }
        return new ChromeDriver(new ChromeOptions());
    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux"));
    }
}