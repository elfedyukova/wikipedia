package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            NAVIGATION_SAVE_BUTTON,
            CANCEL_SYNCHRONIZATION_BUTTON,
            GOT_IT_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        this.waitForElementPresentAndClick(
                NAVIGATION_SAVE_BUTTON,
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                GOT_IT_BUTTON,
                "Cannot find 'Not now' ",
                5
        );
    }

    public void clickMyListsOnIos() {

        this.waitForElementPresentAndClick(
                NAVIGATION_SAVE_BUTTON,
                "Cannot find 'element' Сохранить ",
                10
        );

        this.waitForElementPresentAndClick(
                CANCEL_SYNCHRONIZATION_BUTTON,
                "Cannot find 'element' Закрыть ",
                10
        );
    }
}
