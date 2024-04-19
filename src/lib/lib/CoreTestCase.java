package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.MainPageObject;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

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
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        driver.runAppInBackground(duration);
    }

    private void skipWelcomePageForIosApp() {
        if (Platform.getInstance().isIos()) {
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }

    private void skipWelcomePageForAndroidApp() {
        if (Platform.getInstance().isAndroid()) {
            MainPageObject mainPageObject = new MainPageObject(driver);
            mainPageObject.initSkipInput();
        }
    }
}
