package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_DRIVER_ANDROID = "android";
    private static final String PLATFORM__DRIVER_IOS = "android";

    private static String AppiumUrl = "http://127.0.0.1:4723/";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
       // DesiredCapabilities capabilities =
        this.getCapabilitiesByPlatformEnv();
        //driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {

        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("appium:platformName", "Android");
            capabilities.setCapability("appium:deviceName", "AndroidTestDevice"); //может быть любым для Android
            capabilities.setCapability("appium:platformVersion", "8.1");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability("appium:app",
                    "C:\\Users\\adven\\dev\\mobile\\wikipedia\\wikipedia\\apks\\Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");

            driver = new AndroidDriver(new URL(AppiumUrl), capabilities);

        } else if (platform.equals(PLATFORM_IOS)) {

            capabilities.setCapability("appium:platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 15 Pro");
            capabilities.setCapability("appium:platformVersion", "17.4");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:app",
                    "/Users/e.fedyukova/Library/Developer/Xcode/DerivedData/Wikipedia-fjhsuqlmtigbeoguvdwbhqncinso/Build/Products/Debug-iphonesimulator/Wikipedia.app");

            driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }
}
