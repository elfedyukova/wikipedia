package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_ELEMENT_ON_MAIN_PAGE_IOS = "xpath://XCUIElementTypeSearchField[@name=\"Поиск по Википедии\"]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
        SEARCH_RESULT = "id:org.wikipedia:id/search_results_list";
        SEARCH_CLEAR = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRINGTITLE}'] | //android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRINGDESCRIPTION}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }

    @Override
    public String getSearchExpectedText() {
        return "Search Wikipedia";
    }

}
