package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "xpath://android.widget.TextView[@text='Java (programming language)']",
            FOOTER_ELEMENT = "xpath://android.widget.TextView[@text=\"View article in browser\"]",
            SAVE_BUTTON = "xpath://android.widget.TextView[@content-desc=\"Save\"]",
            ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]",
            CREATE_LIST_BUTTON = "xpath://android.widget.TextView[@text=\"Create new\"]",
            INPUT_NAME_BUTTON = "xpath://android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
            OK_INPUT_BUTTON = "id:android:id/button1",
            NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            NAME_OF_FOLDER_SUBSTRING_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='{SUBSTRING}']",
            SEARCH_BUTTON_ON_ARTICLE_PAGE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_toolbar_button_search\"]";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS*/
    private static String getNameOfFolder(String substring) {
        return NAME_OF_FOLDER_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title",
                15
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article ",
                5
        );
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementPresentAndClick(
                SAVE_BUTTON,
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                SAVE_BUTTON,
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find element 'Add to another reading list' ",
                5
        );

        this.waitForElementPresentAndClick(
                CREATE_LIST_BUTTON,
                "Cannot find element 'Create new' ",
                5
        );

        this.waitForElementPresentAndSendKeys(
                INPUT_NAME_BUTTON,
                name_of_folder,
                "Cannot find 'Input element' ",
                5
        );

        this.waitForElementPresentAndClick(
                INPUT_NAME_BUTTON,
                "Cannot find 'Input element' ",
                5
        );

        this.waitForElementPresentAndClick(
                OK_INPUT_BUTTON,
                "Cannot find 'OK element' ",
                5
        );

    }

    public void closeArticle() {

        this.waitForElementPresentAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Back element' ",
                5
        );

        this.waitForElementPresentAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Back element' ",
                5
        );

    }

    public void addArticleToMyOldList(String name_of_folder) {

        this.waitForElementPresentAndClick(
                SAVE_BUTTON,
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                SAVE_BUTTON,
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find element 'Add to another reading list' ",
                5
        );

        String old_name_of_folder_xpath = getNameOfFolder(name_of_folder);

        this.waitForElementPresentAndClick(
                old_name_of_folder_xpath,
                "Cannot find name of folder " + name_of_folder,
                5
        );
    }
    /* TEMPLATES METHODS*/

    public void returnToTheMainPage() {

        this.waitForElementPresentAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Back element' ",
                5
        );

        this.waitForElementPresentAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Back element' ",
                5
        );

        this.waitForElementPresentAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find 'Back element' ",
                5
        );

    }

    public void clickOnSearchButton() {
        this.waitForElementPresentAndClick(
                (SEARCH_BUTTON_ON_ARTICLE_PAGE),
                "Cannot find 'Search element' ",
                5
        );
    }
}
