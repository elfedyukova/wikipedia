package org.example;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "My first reading list";
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
        navigationUI = NavigationUIFactory.get(driver);
        myListsPageObject = MyListsPageObjectFactory.get(driver);
    }

    @Test
    public void testRemoveArticleFromReadingList() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.closeArticle();
            navigationUI.clickMyLists();
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticleOnIos();
            navigationUI.clickMyListsOnIos();
            myListsPageObject.swipeByArticleToDeleteOnIos(article_title);
        }

    }
}
