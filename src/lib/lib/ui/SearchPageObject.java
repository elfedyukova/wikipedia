package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_SUBSTRING_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                    "and contains(@text, '{SUBSTRING}')]",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']",
            SEARCH_RESULT = "id:org.wikipedia:id/search_results_list",
            SEARCH_CLEAR = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRINGTITLE}'] | //android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRINGDESCRIPTION}']";

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
                SEARCH_INIT_ELEMENT,
                "Cannot find and click 'Search element' ",
                5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementPresentAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForWordPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring,
                5
        );
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresentAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find close element",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5
        );
    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementPresentAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                5
        );
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15

        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element.",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find empty result element."
        );
    }

    public void assertSearchElementHasText() {
        this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                "Search Wikipedia",
                "Другой текст вместо ожидаемого Search Wikipedia"
        );
    }

    public void waitForSearchResults() {
        this.waitForElementPresent(
                SEARCH_RESULT,
                "Cannot find 'element' "
        );
    }

    public void clearSearchResults() {
        this.waitForElementAndClear(
                SEARCH_CLEAR,
                "Cannot clear text in search field",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {

        String search_result_title_description_xpath = getResultTitleDescriptionSearchElement(title, description);

        this.waitForElementPresent(
                search_result_title_description_xpath,
                "Cannot find element with such title and description: " + title + " , " + description,
                15
        );
    }

}
