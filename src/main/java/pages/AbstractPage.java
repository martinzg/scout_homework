package pages;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.List;

@Getter
public abstract class AbstractPage {

    private static final long WAIT_TIMEOUT = 15L;
    protected WebDriver driver;

    public AbstractPage() {
        this.driver = WebDriverUtils.getWebDriver();
    }

    public void open() {
        driver.manage().window().maximize();
        driver.get(getPage().getUrl());
        waitForPageLoadComplete();
    }

    public void close() {
        driver.quit();
    }

    public void smartClick(WebElement webElement) {
        Actions actions = new Actions(driver);
        waitUntilVisible(webElement);
        actions.moveToElement(webElement).click().build().perform();
    }

    public void pause(long seconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(seconds)).perform();
    }

    public void waitUntilVisible(WebElement webElement) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
    }

    public List<WebElement> getElementsWhenVisible(List<WebElement> webElements) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void waitForPageLoadComplete() {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public abstract Pages getPage();
}