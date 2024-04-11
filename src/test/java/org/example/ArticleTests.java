package org.example;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private static final String
            ARTICLE_TITLE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
            " and @text=\"Automation for Apps\"]";

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCompareArticleTitle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "We do not see Java (programming language)",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testCompareArticleOfTitle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

    }

    @Test
    public void testSwipeArticle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);

    }

    @Test
    public void testSwipeArticleToFooter() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");

        MainPageObject.waitForElementPresentAndClick(
                ARTICLE_TITLE,
                "Cannot find 'Skip element' ",
                5
        );

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.swipeToFooter();

    }

    @Test
    public void testSaveTwoArticle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Moscow");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        searchPageObject.clickByArticleWithSubstring("Moscow");
        articlePageObject.addArticleToMyList("City");
        articlePageObject.clickOnSearchButton();

        searchPageObject.typeSearchLine("Saint-Petersburg");
        searchPageObject.clickByArticleWithSubstring("Saint Petersburg");
        articlePageObject.addArticleToMyOldList("City");
        articlePageObject.returnToTheMainPage();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName("City");
        myListsPageObject.swipeByArticleToDelete("Moscow");

    }
}
