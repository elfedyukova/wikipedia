package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

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

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Другой текст вместо ожидаемого Search Wikipedia"
        );

    }

    @Test
    public void searchCancelTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find 'element' "
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementNotPresent(
                By.id("//*[contains(@text,'Search Wikipedia')]"),
                "The element for canceling a search is present",
                5
        );
    }

    @Test
    public void searchClearCancelTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find 'element' "
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear text in search field",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'element' "
        );
    }

    @Test
    public void wordSearchTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        waitForWordPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and contains(@text, 'Java')]"),
                "Cannot find 'elements with text Java' ",
                5
        );
    }

    @Test
    public void compareArticleTitleTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        WebElement titleElement = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title "
        );

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals(
                "We do not see Java (programming language)",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void swipeArticleTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" " +
                        "and @text=\"Java (programming language)\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        verticalSwipeToBottom(700);
        verticalSwipeToBottom(700);
        verticalSwipeToBottom(700);

    }

    @Test
    public void swipeArticleToFooterTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Automation for Apps\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text=\"View article in browser\"]"),
                "Cannot find 'View article in browser' ",
                6
        );

    }

    @Test
    public void removeArticleFromReadingListTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Automation for Apps\"]"),
                "Cannot find article 'Automation for Apps' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@text=\"Create new\"]"),
                "Cannot find element 'Create new' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "My first reading list",
                "Cannot find 'Input element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Cannot find 'Input element' ",
                5
        );

        waitForElementPresentAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK element' ",
                5
        );

        waitForElementPresentAndClick(
                //By.id("Navigate up"),
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]"),
                "Cannot find 'Not now' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" " +
                        "and @text=\"My first reading list\"]"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find 'Article element' ",
                5
        );

        swipeElementToRight(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Test cannot swipe to left"
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot delete save article",
                5
        );

    }

    @Test
    public void saveTwoArticleTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Moscow",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\"" +
                        " and @text=\"Capital and largest city of Russia\"]"),
                "Cannot find article 'Automation for Apps' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@text=\"Create new\"]"),
                "Cannot find element 'Create new' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "City",
                "Cannot find 'Input element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Cannot find 'Input element' ",
                5
        );

        waitForElementPresentAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_toolbar_button_search\"]"),
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Saint-Petersburg",
                "Cannot find 'Search element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Saint Petersburg\"]"),
                "Cannot find article 'Automation for Apps' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Add to another reading list\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"City\"]"),
                "Cannot find element 'Add to another reading list' ",
                5
        );

        waitForElementPresentAndClick(
                //By.id("Navigate up"),
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Back element' ",
                5
        );


        waitForElementPresentAndClick(
                By.xpath("(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]"),
                "Cannot find 'Save element' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]"),
                "Cannot find 'Not now' ",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"City\"]"),
                "Cannot find 'Not now' ",
                5
        );

        swipeElementToRight(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Moscow\"]"),
                "Test cannot swipe to left"
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "",
                5
        );


    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //перегрузка метода
    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresentAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String actual = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actual);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForWordPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    // свайп снизу вверх
    public void verticalSwipeToBottom(int timeOfSwipe) {
        //получаем параметры девайса и передаем параметры экрана в size
        Dimension size = driver.manage().window().getSize();

        int startY = (int) (size.height * 0.70); // получаем начальную точку экрана, находящуюся немного внизу
        int endY = (int) (size.height * 0.30);
        int centerX = size.width / 2; //ось Х не меняется так, как двигаем снизу вверх и может быть одной

        //Создание объектов регистрации событий касания на экране

        //моделирует действия конкретного типа указателя, пальца (TOUCH). В качестве имени указателя используется строка "finger".
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        //объект последовательность действий, которые будут выполнены указателем.
        // Первый параметр - это указатель, который будет использоваться.
        // Второй параметр - это индекс, который указывает на порядковый номер данной последовательности действий среди всех остальных.
        Sequence swipe = new Sequence(finger, 1);

        //Дваигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, startY));
        //Палец прикасакается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe),
                PointerInput.Origin.viewport(), centerX, endY));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }

    protected void swipeUpQuick() {
        verticalSwipeToBottom(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        // цикл постоянно ищет элементы из by и завершится как только будет найден элемент
        while (driver.findElements(by).size() == 0) {
            // ограничение по количеству проведенных свайпов
            if (already_swiped > max_swipes) {
                waitForElementPresent(
                        by,
                        "Cannot find element by swipping up. \n" + error_message,
                        0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    //Свайп справа налево (движение по оси Y не происходит)
    //Нашли элемент, установили местоположение по оси X,Y. После этого передвинуть его по оси X справа налево
    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        // В переменную left_x записали самую левую точку нашего элемента по оси Х
        int left_x = element.getLocation().getX();
        // Находим самую правую точку экрана. element.getSize().getWidth() размер нашего элемента по ширине
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();

        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        //Дваигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), right_x, middle_y));
        //Палец прикасакается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), left_x, middle_y));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));

    }

    //Свайп слева справа (движение по оси Y не происходит). Нашли элемент, установили местоположение по оси X,Y, передвинули его по оси X
    protected void swipeElementToRight(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        // В переменную left_x записали самую левую точку нашего элемента по оси Х
        int left_x = element.getLocation().getX();
        // Находим самую правую точку экрана. element.getSize().getWidth() размер нашего элемента по ширине
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();

        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        //Дваигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), left_x, middle_y));
        //Палец прикасается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), right_x, middle_y));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }

}

