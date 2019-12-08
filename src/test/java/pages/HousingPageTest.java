package pages;

import extension.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static extension.SeleniumExtension.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
class HousingPageTest {

    private static final String SEARCH_INPUT = "3 bedroom";
    private static final String SORT_BY_PRICE_ASC = "sort by price, lowest to highest";
    private static final String SORT_BY_PRICE_DESC = "sort by price, highest to lowest";
    private HousingPage page;

    @BeforeEach
    public void init() {
        page = new HousingPage(getDriver());
        page.open();
    }

    @Test
    @DisplayName("Check default Sorting options")
    public void testDefaultSortingOptions() {
        page.getSortDropdown().click();

        List<String> sortOptions = page.getSortOptions();
        assertEquals(4, sortOptions.size(), "check available sort options");
        assertEquals("show newest matches first", sortOptions.get(1), "check newest");
        assertEquals("sort by price, lowest to highest", sortOptions.get(2), "check price acs");
        assertEquals("sort by price, highest to lowest", sortOptions.get(3), "check price desc");
    }

    @Test
    @DisplayName("Check Sorting options after Search")
    public void testSortingOptionsAfterSearch() {
        page.getSearchBox().sendKeys(SEARCH_INPUT);
        page.selectFromSearchAutoSuggest(SEARCH_INPUT);
        page.waitToBeLoaded();
        page.getSortDropdown().click();

        List<String> sortOptions = page.getSortOptions();
        assertEquals(5, sortOptions.size(), "check available sort options");
        assertEquals("show upcoming open houses", sortOptions.get(0), "check upcoming");
        assertEquals("show newest matches first", sortOptions.get(1), "check newest");
        assertEquals("show most relevant matches first", sortOptions.get(2), "check most relevant");
        assertEquals("sort by price, lowest to highest", sortOptions.get(3), "check price acs");
        assertEquals("sort by price, highest to lowest", sortOptions.get(4), "check price desc");
    }

    @Test
    @DisplayName("Check Sorting by Price ascending")
    public void testSortingByPriceAsc() {
        page.getSortDropdown().click();
        page.selectFromSortDropdown(SORT_BY_PRICE_ASC);
        page.waitToBeLoaded();

        assertEquals(true, page.isPricesSortedAsc(), "is prices sorted from lowest to highest");
    }

    @Test
    @DisplayName("Check Sorting by Price descending")
    public void testSortingByPriceDesc() {
        page.getSortDropdown().click();
        page.selectFromSortDropdown(SORT_BY_PRICE_DESC);
        page.waitToBeLoaded();

        assertEquals(true, page.isPricesSortedDesc(), "is prices sorted from highest to lowest");
    }
}