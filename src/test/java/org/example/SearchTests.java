package org.example;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.TitleDescriptionPair;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class SearchTests extends CoreTestCase {

    private SearchPageObject searchPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = SearchPageObjectFactory.get(driver); // Инициализация переменной
    }

    @Test
    public void testAmountOfNotEmptySearch() {
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
        searchPageObject.initSearchInput();
        String search_line = "qqqqqqqqqq";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        //searchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testElementHasText() {
        searchPageObject.initSearchInput();
        searchPageObject.assertSearchElementHasText();
    }

    @Test
    public void testSearchCancel() {
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchClearCancel() {
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults();
        searchPageObject.clearSearchResults();
        searchPageObject.waitForSearchElementIsPresentOnMainPageIos();
    }

    @Test
    public void testWordSearch() {
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testSearchByTitleAndDescription() {
        searchPageObject.initSearchInput();
        String search_line = "IT";
        searchPageObject.typeSearchLine(search_line);
        List<TitleDescriptionPair> expectedTexts = searchPageObject.getSearchExpectedTexts();

        for (TitleDescriptionPair expectedText : expectedTexts) {
            searchPageObject.waitForElementByTitleAndDescription(expectedText.getTitle(), expectedText.getDescription());
        }
    }
}
