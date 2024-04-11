package org.example.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() {

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForFreeWikipedia();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWaysToExplore();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForSearchOnSomeLanguage();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForDataPrivacy();
        welcomePageObject.clickStartButton();

    }

}
