package org.example;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCompareArticleTitle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
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
    public void testSwipeArticle() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);

    }
}
