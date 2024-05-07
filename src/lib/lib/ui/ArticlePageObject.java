package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            CREATE_LIST_BUTTON,
            INPUT_NAME_BUTTON,
            OK_INPUT_BUTTON,
            NAVIGATE_UP_BUTTON,
            NAME_OF_FOLDER_SUBSTRING_TPL,
            ARTICLE_TITLE,
            CANCEL_BUTTON,
            SEARCH_BUTTON_ON_ARTICLE_PAGE;


    public ArticlePageObject(RemoteWebDriver driver) {
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
        if (lib.Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIos()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isMw()){
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article ",
                    40
            );
        } else {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article ",
                    5
            );
        }
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

    public void addArticlesToMySaved() {
        this.waitForElementPresentAndClick(SAVE_BUTTON, "Cannot find Save element on IOS", 5);
    }

    public void closeArticleOnIos() {
        this.waitForElementPresentAndClick(NAVIGATE_UP_BUTTON, "", 5);
        this.waitForElementPresentAndClick(CANCEL_BUTTON, "Cannot click on Отменить", 5);
    }
}
