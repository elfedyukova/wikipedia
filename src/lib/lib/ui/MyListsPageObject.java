package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"{FOLDER_NAME}\"]",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
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

}
