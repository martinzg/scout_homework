package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PageUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HousingPage extends Page {

    @FindBy(id = "query")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@class='searchbtn']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='search-sort']//ul[@class='dropdown-list']")
    private WebElement sortDropdown;

    private String SORT_OPTIONS_LOCATOR = "//li[contains(@class, 'dropdown-item mode ')]//a";
    private String SEARCH_AUTOCOMPLETE_ITEMS_LOCATOR = "//ul[contains(@class, 'ui-autocomplete')]//a";
    private String PRICE_LOCATOR = "//span[@class='result-meta']//span[@class='result-price']";

    public HousingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Pages getPage() {
        return Pages.HOUSING;
    }

    public List<String> getSortOptions() {
        return PageUtils.getElementsWhenVisible(By.xpath(SORT_OPTIONS_LOCATOR))
                .stream()
                .map(x -> x.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public void selectFromSortDropdown(String sortOption) {
        PageUtils.getElementsWhenVisible(By.xpath(SORT_OPTIONS_LOCATOR))
                .stream()
                .filter(x -> x.getAttribute("title").equals(sortOption))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void selectFromSearchAutoSuggest(String searchText) {
        PageUtils.getElementsWhenVisible(By.xpath(SEARCH_AUTOCOMPLETE_ITEMS_LOCATOR))
                .stream()
                .filter(x -> x.getText().equals(searchText))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public Boolean isPricesSortedAsc() {
        List<Integer> prices = getPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public Boolean isPricesSortedDesc() {
        List<Integer> prices = getPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getPrices() {
        return driver.findElements(By.xpath(PRICE_LOCATOR))
                .stream()
                .map(WebElement::getText)
                .filter(x -> x.contains("€"))
                .map(x -> x.substring(1))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}