package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            NAVIGATION_SAVE_BUTTON = "\t\n" +
            "(//android.widget.FrameLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_container\"])[2]",
            GOT_IT_BUTTON = "org.wikipedia:id/negativeButton";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        this.waitForElementPresentAndClick(
                By.xpath(NAVIGATION_SAVE_BUTTON),
                "Cannot find 'Save element' ",
                5
        );

        this.waitForElementPresentAndClick(
                By.id(GOT_IT_BUTTON),
                "Cannot find 'Not now' ",
                5
        );
    }
}
