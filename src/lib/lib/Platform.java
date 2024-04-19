package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    //метод отвечающий за выбор нужного драйвера
    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);

        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIos()) {
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos() {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice"); //может быть любым для Android
        capabilities.setCapability("appium:platformVersion", "8.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app",
                "C:\\Users\\adven\\dev\\mobile\\wikipedia\\wikipedia\\apks\\Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 15 Pro");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app",
                "/Users/e.fedyukova/Library/Developer/Xcode/DerivedData/Wikipedia-fjhsuqlmtigbeoguvdwbhqncinso/Build/Products/Debug-iphonesimulator/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }


}
