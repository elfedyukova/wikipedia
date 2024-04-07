package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//android.widget.TextView[@text='Java (programming language)']",
            FOOTER_ELEMENT = "//android.widget.TextView[@text=\"View article in browser\"]",
            SAVE_BUTTON = "//android.widget.TextView[@content-desc=\"Save\"]",
            ADD_TO_MY_LIST_BUTTON = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]",
            CREATE_LIST_BUTTON = "//android.widget.TextView[@text=\"Create new\"]",
            INPUT_NAME_BUTTON = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
            OK_INPUT_BUTTON = "android:id/button1",
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                By.xpath(TITLE),
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
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article ",
                5
        );
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementPresentAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.xpath(CREATE_LIST_BUTTON),
                "Cannot find element 'Create new' ",
                5
        );

        this.waitForElementPresentAndSendKeys(
                By.xpath(INPUT_NAME_BUTTON),
                name_of_folder,
                "Cannot find 'Input element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.xpath(INPUT_NAME_BUTTON),
                "Cannot find 'Input element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.id(OK_INPUT_BUTTON),
                "Cannot find 'OK element' ",
                5
        );

    }

    public void closeArticle() {

        this.waitForElementPresentAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find 'Back element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find 'Back element' ",
                5
        );

    }
}
