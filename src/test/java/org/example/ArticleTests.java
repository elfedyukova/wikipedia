package org.example;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private MainPageObject MainPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        searchPageObject = SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
        navigationUI = NavigationUIFactory.get(driver);
        myListsPageObject = MyListsPageObjectFactory.get(driver);
    }

    @Test
    public void testCompareArticleTitle() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        String article_title = articlePageObject.getArticleTitle();
        String expectedArticleName = searchPageObject.getSearchExpectedArticleText();

        assertEquals(
                "We do not see article title ",
                expectedArticleName,
                article_title
        );
    }

    @Test
    public void testCompareArticleOfTitle() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        articlePageObject.waitForTitleElement();
    }

    @Test
    public void testSwipeArticle() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        articlePageObject.waitForTitleElement();

        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);

    }

    @Test
    public void testSwipeArticleToFooter() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        articlePageObject.swipeToFooter();

    }

    @Test
    public void testSaveTwoArticle() {

        searchPageObject.search("Москва");

        if (Platform.getInstance().isAndroid()) {

            articlePageObject.addArticleToMyList("City");
            articlePageObject.clickOnSearchButton();
            searchPageObject.typeSearchLine("Санкт-Петербург");
            searchPageObject.clickByArticleWithSubstring("Санкт-Петербург");
            articlePageObject.addArticleToMyOldList("City");
            articlePageObject.returnToTheMainPage();
            navigationUI.clickMyLists();
            myListsPageObject.openFolderByName("City");
            myListsPageObject.swipeByArticleToDelete("Moscow");

        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticleOnIos();
            searchPageObject.search("Санкт-Петербург");
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticleOnIos();

            navigationUI.clickMyListsOnIos();
            myListsPageObject.checkOnOfTheTwoSavedArticleOnIos("Санкт-Петербург");

        }

    }
}
