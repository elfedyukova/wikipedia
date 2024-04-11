package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

    private static String AppiumUrl = "http://127.0.0.1:4723/";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 15 Pro");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app",
                "/Users/e.fedyukova/Library/Developer/Xcode/DerivedData/Wikipedia-fjhsuqlmtigbeoguvdwbhqncinso/Build/Products/Debug-iphonesimulator/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumUrl), capabilities);
        //this.rotateScreenPortrait(); перед запуском естов убеждаемся, что девайс в портретной ориентации
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
}
