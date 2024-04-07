package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice"); //может быть любым для Android
        capabilities.setCapability("appium:platformVersion", "8.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app",
                "C:\\Users\\adven\\dev\\mobile\\wikipedia\\wikipedia\\apks\\Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");

        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
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
