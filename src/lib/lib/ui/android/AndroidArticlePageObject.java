package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://android.widget.TextView[@text='Java (programming language)']";
        FOOTER_ELEMENT = "xpath://android.widget.TextView[@text=\"View article in browser\"]";
        SAVE_BUTTON = "xpath://android.widget.TextView[@content-desc=\"Save\"]";
        ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]";
        CREATE_LIST_BUTTON = "xpath://android.widget.TextView[@text=\"Create new\"]";
        INPUT_NAME_BUTTON = "xpath://android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]";
        OK_INPUT_BUTTON = "id:android:id/button1";
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
        NAME_OF_FOLDER_SUBSTRING_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='{SUBSTRING}']";
        SEARCH_BUTTON_ON_ARTICLE_PAGE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_toolbar_button_search\"]";
        ARTICLE_TITLE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                " and @text=\"Automation for Apps\"]";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}


