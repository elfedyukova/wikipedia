package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SimpleTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice"); //может быть любым для Android
        capabilities.setCapability("appium:platformVersion", "8.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app",
                "C:\\Users\\adven\\dev\\mobile\\wikipedia\\wikipedia\\apks\\Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void elementHasTextTest() {

        waitForElementByIdAndClick(
                "org.wikipedia:id/fragment_onboarding_skip_button",
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search element' ",
                5
        );

        assertElementHasText(
                "//*[contains(@text,'Search Wikipedia')]",
                "Search Wikipedia",
                "Другой текст вместо ожидаемого Search Wikipedia"
        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds) {
        By by = By.id(id);
        return waitForElementPresentAndClick(by, error_message, timeoutInSeconds);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds) {
        By by = By.xpath(xpath);
        return waitForElementPresentAndClick(by, error_message, timeoutInSeconds);
    }

    private void assertElementHasText(String xpath, String expectedText, String errorMessage) {
        By locator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        String actual = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actual);
    }

}