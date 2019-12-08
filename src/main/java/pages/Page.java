package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.PageUtils;

import java.time.Duration;

public abstract class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        PageUtils.openPage(getPage().getUrl());
        waitToBeLoaded();
    }

    public void smartClick(WebElement webElement) {
        Actions actions = new Actions(driver);
        PageUtils.waitUntilVisible(webElement);
        actions.moveToElement(webElement).click().build().perform();
    }

    public void pause(long seconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(seconds)).perform();
    }

    public void waitToBeLoaded() {
        PageUtils.waitForPageLoadComplete();
    }

    public abstract Pages getPage();
}
