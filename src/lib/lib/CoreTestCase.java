package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.MainPageObject;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        //this.rotateScreenPortrait(); перед запуском естов убеждаемся, что девайс в портретной ориентации
        Platform platform = Platform.getInstance(); // Получаем инстанс платформы
        if (platform.isAndroid()) {
            this.skipWelcomePageForAndroidApp(); // Если платформа - Android
        } else if (platform.isIos()) {
            this.skipWelcomePageForIosApp(); // Если платформа - iOS
        } else if (platform.isMw()) {
            this.openWikiWebPageForMobileWeb();
        } else {
            throw new Exception("Cannot detect type of the Platform. Platform value: ");
        }

    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds) {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            Duration duration = Duration.ofSeconds(seconds);
            driver.runAppInBackground(duration);
        } else {
            System.out.println("Method backgroundApp does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForIosApp() {
        if (Platform.getInstance().isIos()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMw()) {
           driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForAndroidApp() {
        if (Platform.getInstance().isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            MainPageObject mainPageObject = new MainPageObject(driver);
            mainPageObject.initSkipInput();
        }
    }
}
