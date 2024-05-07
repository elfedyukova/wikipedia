package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        NAVIGATION_SAVE_BUTTON = "id:Сохранено";////XCUIElementTypeButton[@name="Сохранено"]
        GOT_IT_BUTTON = "id:org.wikipedia:id/negativeButton";
        CANCEL_SYNCHRONIZATION_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Закрыть\"]";//XCUIElementTypeButton[@name="Закрыть"]
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
