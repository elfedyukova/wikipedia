package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresentAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_xpath,
                "Cannot find 'Article element' ",
                5
        );

        this.swipeElementToRight(
                article_xpath,
                "Test cannot swipe to left"
        );

        this.swipeByArticleToDisappear(article_title);

    }

    public void swipeByArticleToDisappear(String article_title) {

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Cannot delete save article",
                5
        );
    }

    public void swipeByArticleToDeleteOnIos(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_xpath,
                "Cannot find 'Article element' " + article_xpath,
                5
        );

        this.swipeElementToLeft(
                article_xpath,
                "Test cannot swipe to left"
        );

        this.swipeByArticleToDisappearOnIos(article_title);

    }

    protected void swipeByArticleToDisappearOnIos(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                article_xpath,
                "Cannot delete save article " + article_xpath,
                15
        );
    }

    public void checkOnOfTheTwoSavedArticleOnIos(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_xpath,
                "Cannot find 'Article element' ",
                5
        );

    }
}
