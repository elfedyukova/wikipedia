package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Поиск по Википедии";
        SEARCH_INPUT = "id:Поиск по Википедии";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name=\'Отменить\']";
        SEARCH_RESULT_SUBSTRING_TPL = "contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath:XCUIElementTypeOther";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name=\'Ничего не найдено\']";
        SEARCH_RESULT = "id:org.wikipedia:id/search_results_list";
        SEARCH_CLEAR = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRINGTITLE}'] | //android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRINGDESCRIPTION}']";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }

}
