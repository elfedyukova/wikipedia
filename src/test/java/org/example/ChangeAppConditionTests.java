package org.example;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring();

        String title_before_rotation = articlePageObject.getArticleTitle();

        try {
            this.rotateScreenLandscape();
            String title_after_rotation = articlePageObject.getArticleTitle();

            assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );

            this.rotateScreenPortrait();
            String title_after_second_rotation = articlePageObject.getArticleTitle();

            assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
        } finally {
            this.rotateScreenPortrait();
        }

    }

    @Test
    public void testCheckSearchArticleInBackground() {

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");

        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Java");

    }

}
