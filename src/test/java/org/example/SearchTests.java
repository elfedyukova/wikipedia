package org.example;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.TitleDescriptionPair;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class SearchTests extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
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
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "qqqqqqqqqq";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        //searchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testElementHasText() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.assertSearchElementHasText();
    }

    @Test
    public void testSearchCancel() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchClearCancel() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults();
        searchPageObject.clearSearchResults();
        searchPageObject.waitForSearchElementIsPresentOnMainPageIos();
    }

    @Test
    public void testWordSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testSearchByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "IT";
        searchPageObject.typeSearchLine(search_line);
        //searchPageObject.waitForElementByTitleAndDescription("Italy", "Country in Southern Europe");
        //searchPageObject.waitForElementByTitleAndDescription("Itunes", "Apple's media library and media player software");
        //searchPageObject.waitForElementByTitleAndDescription("Italy national football team", "Men's association football team");
        List<TitleDescriptionPair> expectedTexts = searchPageObject.getSearchExpectedTexts();

        for (TitleDescriptionPair expectedText : expectedTexts) {
            searchPageObject.waitForElementByTitleAndDescription(expectedText.getTitle(), expectedText.getDescription());
        }
    }
}
