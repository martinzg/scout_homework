package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.URL;

public class WebDriverUtils {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String BROWSER = System.getProperty("browser");

    private static final String FIREFOX_WINDOWS_DRIVER_FILENAME = "geckodriver_win64.exe";
    private static final String FIREFOX_LINUX_DRIVER_FILENAME = "geckodriver_lin64";
    private static final String CHROME_WINDOWS_DRIVER_FILENAME = "chromedriver_win.exe";
    private static final String CHROME_LINUX_DRIVER_FILENAME = "chromedriver_lin";


    public static WebDriver getWebDriver() {
        if (isWindows()) {
            return configureWebDriver(FIREFOX_WINDOWS_DRIVER_FILENAME, CHROME_WINDOWS_DRIVER_FILENAME);
        } else if (isUnix()) {
            return configureWebDriver(FIREFOX_LINUX_DRIVER_FILENAME, CHROME_LINUX_DRIVER_FILENAME);
        }
        throw new IllegalArgumentException("unsupported OS");
    }

    private static WebDriver configureWebDriver(String firefoxDriverFilename, String chromeDriverFilename) {
        if (BROWSER == null || BROWSER.toLowerCase().equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", getDriverLocation(firefoxDriverFilename));
            return new FirefoxDriver(new FirefoxOptions());
        } else if(BROWSER.toLowerCase().equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", getDriverLocation(chromeDriverFilename));
            return new ChromeDriver(new ChromeOptions());
        }
        throw new IllegalArgumentException("unsupported Browser");
    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux"));
    }

    private static String getDriverLocation(String filename) {
        ClassLoader classLoader = WebDriverUtils.class.getClassLoader();
        URL resource = classLoader.getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }
        return resource.getPath();
    }
}