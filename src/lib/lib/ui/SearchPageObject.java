package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_SUBSTRING_TPL = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                    "and contains(@text, '{SUBSTRING}')]",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SEARCH_RESULT = "org.wikipedia:id/search_results_list",
            SEARCH_CLEAR = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRINGTITLE}'] | //android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRINGDESCRIPTION}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultTitleDescriptionSearchElement(String substringTitle, String substringDescription) {
        return SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL
                .replace("{SUBSTRINGTITLE}", substringTitle)
                .replace("{SUBSTRINGDESCRIPTION}", substringDescription);
    }
    /* TEMPLATES METHODS*/

    public void initSearchInput() {
        this.waitForElementPresentAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click 'Search element' ",
                5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementPresentAndSendKeys(
                By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForWordPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring,
                5
        );
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresentAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find close element",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present",
                5
        );
    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementPresentAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                5
        );
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15

        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element.",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find empty result element."
        );
    }

    public void assertSearchElementHasText() {
        this.assertElementHasText(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Search Wikipedia",
                "Другой текст вместо ожидаемого Search Wikipedia"
        );
    }

    public void waitForSearchResults() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULT),
                "Cannot find 'element' "
        );
    }

    public void clearSearchResults() {
        this.waitForElementAndClear(
                By.id(SEARCH_CLEAR),
                "Cannot clear text in search field",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description){

        String search_result_title_description_xpath = getResultTitleDescriptionSearchElement(title, description);;

        this.waitForElementPresent(
                By.xpath(search_result_title_description_xpath),
                "Cannot find element with such title and description: " + title + " , " + description,
                15
        );
    }

}
