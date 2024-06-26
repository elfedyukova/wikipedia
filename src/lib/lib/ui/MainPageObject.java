package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainPageObject {

    private static final String
            SKIP_INPUT = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void initSkipInput() {
        this.waitForElementPresentAndClick(
                SKIP_INPUT,
                "Cannot find Skip element",
                5
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorString(locator);
        // System.out.println(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //перегрузка метода
    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementPresentAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementPresentAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void assertElementHasText(String locator, String expectedText, String errorMessage) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String actual = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actual);
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForWordPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public int getAmountOfElements(String locator) {

        By by = this.getLocatorString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message) {

        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' supposed to be not present ";
            throw new AssertionError(default_message + "" + error_message);
        }

    }

    public void assertElementPresent(By by) {

        WebElement element = driver.findElement(by);
        String title_of_element = element.getText();

        if (title_of_element.isEmpty()) {
            throw new AssertionError("Элемент '" + by.toString() + "' не найден!");
        }
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

    public void swipeUpQuick() {
        verticalSwipeToBottom(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorString(locator);
        int already_swiped = 0;
        // цикл постоянно ищет элементы из by и завершится как только будет найден элемент
        while (driver.findElements(by).size() == 0) {
            // ограничение по количеству проведенных свайпов
            if (already_swiped > max_swipes) {
                waitForElementPresent(
                        locator,
                        "Cannot find element by swipping up. \n" + error_message,
                        0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    public void swipeElementToLeft(String locator, String error_message) {

        WebElement element = waitForElementPresent(locator, error_message, 10);

        int left_x = element.getLocation().x;
        int right_x = left_x + element.getSize().width;
        int upper_y = element.getLocation().y;
        int lower_y = upper_y + element.getSize().height;
        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence actions = new Sequence(finger, 1);

        // Стартовая позиция
        actions.addAction(finger.createPointerMove(
                Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),
                right_x,
                middle_y)
        );

        // Опустить палец на экран
        actions.addAction(finger.createPointerDown(0));

        // Сдвинуть палец к точке (left_x, middle_y) на Android
        if (Platform.getInstance().isAndroid()) {
            actions.addAction(finger.createPointerMove(
                    Duration.ofMillis(100),
                    PointerInput.Origin.viewport(),
                    left_x,
                    middle_y)
            );
            // Сдвинуть палец влево на всю ширину экрана на iOS
        } else {
            int offset_x = (-1 * element.getSize().width);
            actions.addAction(finger.createPointerMove(
                    Duration.ofMillis(100),
                    PointerInput.Origin.viewport(),
                    offset_x,
                    middle_y)
            );
        }

        // Поднять палец
        actions.addAction(finger.createPointerUp(0));

        driver.perform(Collections.singletonList(actions));
    }

    //Свайп слева справа (движение по оси Y не происходит). Нашли элемент, установили местоположение по оси X,Y, передвинули его по оси X
    public void swipeElementToRight(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
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

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        if(Platform.getInstance().isMw()){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Object is_result = javascriptExecutor.executeScript("return window.pageYOfset");
            element_location_by_y -= Integer.parseInt(is_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void scrollWebPageUp(){
        if(Platform.getInstance().isMw()){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        WebElement webElement = this.waitForElementPresent(locator, error_message);

        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;

            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, webElement.isDisplayed());
            }
        }
    }

    private By getLocatorString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type locator. Locator: " + locator_with_type);
        }
    }
}
