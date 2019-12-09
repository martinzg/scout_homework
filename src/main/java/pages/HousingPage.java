package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class HousingPage extends AbstractPage {

    @FindBy(id = "query")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@class='searchbtn']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='search-sort']//ul[@class='dropdown-list']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//li[contains(@class, 'dropdown-item mode ')]//a")
    private List<WebElement> sortOptions;

    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]//a")
    private List<WebElement> searchAutoSuggestItems;

    @FindBy(xpath = "//span[@class='result-meta']//span[@class='result-price']")
    private List<WebElement> prices;

    private String PRICE_LOCATOR = "//span[@class='result-meta']//span[@class='result-price']";

    public HousingPage() {
        PageFactory.initElements(driver, this);
    }

    public Pages getPage() {
        return Pages.HOUSING;
    }

    public List<String> getSortOptionsList() {
        return getElementsWhenVisible(sortOptions)
                .stream()
                .map(x -> x.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public void selectFromSortDropdown(String sortOption) {
        getElementsWhenVisible(sortOptions)
                .stream()
                .filter(x -> x.getAttribute("title").equals(sortOption))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void selectFromSearchAutoSuggest(String searchText) {
        getElementsWhenVisible(searchAutoSuggestItems)
                .stream()
                .filter(x -> x.getText().equals(searchText))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public Boolean isPricesSortedAsc() {
        List<Integer> prices = getPrices();
        return IntStream.range(0, prices.size() - 1).noneMatch(i -> prices.get(i) > prices.get(i + 1));
    }

    public Boolean isPricesSortedDesc() {
        List<Integer> prices = getPrices();
        return IntStream.range(0, prices.size() - 1).noneMatch(i -> prices.get(i) < prices.get(i + 1));
    }

    private List<Integer> getPrices() {
        return getElementsWhenVisible(prices)
                .stream()
                .map(WebElement::getText)
                .filter(x -> x.contains("€"))
                .map(x -> x.substring(1))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}