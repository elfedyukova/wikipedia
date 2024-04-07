package org.example;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


public class SimpleTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSwipeArticleToFooter() {

        MainPageObject.initSkipInput();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        //searchPageObject.clickByArticleWithSubstring("Automation for Apps");

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Automation for Apps\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.swipeToFooter();

    }

    @Test
    public void testCompareArticleOfTitle() {

        MainPageObject.initSkipInput();

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        String search_text = "Java";

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_text,
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find 'Skip element' ",
                5
        );


        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "hg",
                5

        );

        String title_locator = "//android.widget.TextView[@text='Java (programming language)']";

        MainPageObject.assertElementPresent(
                By.xpath(title_locator)
        );
    }

    @Test
    public void testSaveTwoArticle() {

        MainPageObject.initSkipInput();

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Moscow",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Capital and largest city of Russia\"]"),
                "Cannot find article 'Automation for Apps' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@text=\"Create new\"]"),
                "Cannot find element 'Create new' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "City",
                "Cannot find 'Input element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Cannot find 'Input element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_toolbar_button_search\"]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Saint-Petersburg",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Saint Petersburg\"]"),
                "Cannot find article 'Automation for Apps' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"City\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                //By.id("Navigate up"),
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );


        MainPageObject.waitForElementPresentAndClick(
                By.xpath("(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]"),
                "Cannot find 'Save element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]"),
                "Cannot find 'Not now' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"City\"]"),
                "Cannot find 'Not now' ",
                5
        );

        MainPageObject.swipeElementToRight(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Moscow\"]"),
                "Test cannot swipe to left"
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "",
                5
        );

    }

}

