package lib.ui;

import io.appium.java_client.AppiumDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_ELEMENT_ON_MAIN_PAGE_IOS,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT,
            SEARCH_CLEAR,
            SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL;

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
                10
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

    public void waitForSearchElementIsPresentOnMainPageIos() {
        this.waitForElementPresent(
                SEARCH_ELEMENT_ON_MAIN_PAGE_IOS,
                "Search cancel button is still present",
                5
        );
    }

    public abstract String getSearchExpectedArticleText();

    public void clickByArticleWithSubstring() {
        //String search_result_xpath = getResultSearchElement(substring);
        String search_result_xpath = getResultSearchElement(getSearchExpectedArticleText());

        this.waitForElementPresentAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + getSearchExpectedArticleText(),
                5
        );
    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementPresentAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + search_result_xpath,
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
                " Cannot find empty result element."
        );
    }

    public abstract String getSearchExpectedText();

    public void assertSearchElementHasText() {
        this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                getSearchExpectedText(),
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

    public abstract List<TitleDescriptionPair> getSearchExpectedTexts();

}
