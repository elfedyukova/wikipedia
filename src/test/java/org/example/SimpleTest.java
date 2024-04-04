package org.example;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class SimpleTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testElementHasText() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Другой текст вместо ожидаемого Search Wikipedia"
        );

    }

    @Test
    public void testSearchCancel() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find 'element' "
        );

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("//*[contains(@text,'Search Wikipedia')]"),
                "The element for canceling a search is present",
                5
        );
    }

    @Test
    public void testSearchClearCancel() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find 'element' "
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear text in search field",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'element' "
        );
    }

    @Test
    public void testWordSearch() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForWordPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and contains(@text, 'Java')]"),
                "Cannot find 'elements with text Java' ",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

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

        WebElement titleElement = MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title ",
                10
        );

        String articleTitle = titleElement.getAttribute("text");

        Assert.assertEquals(
                "We do not see Java (programming language)",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void testSwipeArticle() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);
        MainPageObject.verticalSwipeToBottom(700);

    }

    @Test
    public void testSwipeArticleToFooter() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Automation for Apps\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text=\"View article in browser\"]"),
                "Cannot find 'View article in browser' ",
                6
        );

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        String search_line = "Linkin park discography";

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search element' ",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15

        );

        int amount_of_search_elements = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_elements > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        String search_line = "qqqqqqqqqq";

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search element' ",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find anything by the request " + empty_result_label,
                15

        );

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );

    }

    @Test
    public void testCompareArticleOfTitle() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

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
    public void testRemoveArticleFromReadingList() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search element' ",
                5
        );

        MainPageObject.waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Automation for Apps\"]"),
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

        String name_of_folder = "My first reading list";

        MainPageObject.waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                name_of_folder,
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
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" " +
                        "and @text='" + name_of_folder + "']"),
                "Cannot find 'Skip element' ",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find 'Article element' ",
                5
        );

        MainPageObject.swipeElementToRight(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Test cannot swipe to left"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot delete save article",
                5
        );

    }

    @Test
    public void testSaveTwoArticle() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

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

    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

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
                "Cannot find text " + search_text,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article",
                15
        );

        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);

            String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                    "text",
                    "Cannot find article",
                    15
            );

            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );

            driver.rotate(ScreenOrientation.PORTRAIT);

            String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                    "text",
                    "Cannot find article",
                    15
            );

            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
        } finally {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    }

    @Test
    public void testCheckSearchArticleInBackground() {

        MainPageObject.waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

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

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find text " + search_text,
                15
        );

        //преобразование duration в реальное время в секундах
        Duration duration = Duration.ofSeconds(2);
        driver.runAppInBackground(duration);

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find text article after returning from background",
                15
        );

    }


}

