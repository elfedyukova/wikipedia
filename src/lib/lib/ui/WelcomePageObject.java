package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Свободная энциклопедия",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:Новые способы изучения",
            STEP_SEARCH_LANGUAGES = "id:Искать на более чем 300 языках",
            STEP_DATA_PRIVACY = "id:Data & Privacy",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Начать\"]",
            NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Далее\"]",
            SKIPP_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Пропустить\"]";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForFreeWikipedia() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find text Свободная энциклопедия",
                10);
    }

    public void clickNextButton() {
        this.waitForElementPresentAndClick(
                NEXT_BUTTON,
                "Cannot find and click Next button",
                10);
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find text Свободная энциклопедия",
                10);
    }

    public void waitForSearchOnSomeLanguage() {
        this.waitForElementPresent(
                STEP_SEARCH_LANGUAGES,
                "Cannot find text Свободная энциклопедия",
                10);
    }

    public void waitForDataPrivacy() {
        this.waitForElementPresent(
                STEP_DATA_PRIVACY,
                "Cannot find text Data & Privacy",
                10);
    }

    public void clickStartButton() {
        this.waitForElementPresentAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click start button",
                10);
    }

    public void clickSkip() {
        this.waitForElementPresentAndClick(
                SKIPP_BUTTON,
                "Cannot find and click skipp button " + SKIPP_BUTTON,
                5);
    }
}
