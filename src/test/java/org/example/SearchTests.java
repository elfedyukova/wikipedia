package org.example;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_elements = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_elements > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_line = "qqqqqqqqqq";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testElementHasText() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.assertSearchElementHasText();

    }

    @Test
    public void testSearchCancel() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.waitForCancelButtonToDisappear();
    }



    @Test
    public void testSearchClearCancel() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find 'element' "
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear text in search field",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'element' "
        );
    }

    @Test
    public void testWordSearch() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
    }
}
