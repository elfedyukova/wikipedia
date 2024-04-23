package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        NAVIGATION_SAVE_BUTTON = "xpath:\t\n" +
                "(//android.widget.FrameLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_container\"])[2]";
        GOT_IT_BUTTON = "id:org.wikipedia:id/negativeButton";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
