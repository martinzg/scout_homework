package utils;

import extension.SeleniumExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageUtils {

    private static final long WAIT_TIMEOUT = 15L;
    private static WebDriver driver;

    public static void openPage(String url) {
        driver = SeleniumExtension.getDriver();
        driver.get(url);
    }

    public static void waitUntilVisible(By locator) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilVisible(WebElement webElement) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static List<WebElement> getElementsWhenVisible(By locator) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForPageLoadComplete() {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(new ExpectedCondition<Object>() {
               @Override
               public Boolean apply(WebDriver driver) {
                   return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
               }
            });
    }
}