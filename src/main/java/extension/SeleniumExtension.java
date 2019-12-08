package extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import static utils.WebDriverUtils.configureWebDriver;

public class SeleniumExtension implements BeforeEachCallback, AfterEachCallback {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        driver = configureWebDriver();
        driver.manage().window().maximize();
    }

    public void afterEach(ExtensionContext extensionContext) throws Exception {
        driver.quit();
    }
}