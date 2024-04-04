package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //перегрузка метода
    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementPresentAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String actual = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actual);
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForWordPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message) {

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
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

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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
    public void swipeElementToLeft(By by, String error_message) {
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
    public void swipeElementToRight(By by, String error_message) {
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