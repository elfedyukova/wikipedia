package lib.ui.ios;

import lib.ui.SearchPageObject;
import lib.ui.TitleDescriptionPair;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.List;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Поиск по Википедии";
        SEARCH_INPUT = "id:Поиск по Википедии";
        SEARCH_ELEMENT_ON_MAIN_PAGE_IOS = "xpath://XCUIElementTypeSearchField[@name=\"Поиск по Википедии\"]";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name=\'Отменить\']";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name=\'Ничего не найдено\']";
        SEARCH_RESULT = "xpath://XCUIElementTypeApplication[@name=\"Википедия\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView";
        SEARCH_CLEAR = "xpath://XCUIElementTypeStaticText[@name=\"Отменить\"]";
        SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRINGTITLE}'] | //XCUIElementTypeStaticText[@name='{SUBSTRINGDESCRIPTION}']";
    }

    private String articleName;

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getSearchExpectedText() {
        return "Поиск по Википедии";
    }

    @Override
    public String getSearchExpectedArticleText() {
        return "Java";
    }

    @Override
    public List<TitleDescriptionPair> getSearchExpectedTexts() {
        // создаем список пар "название-описание" для iOS
        return Arrays.asList(
                new TitleDescriptionPair("It Takes Two", "Компьютерная игра 2021 года в жанре action-adventure"),
                new TitleDescriptionPair("It Must Have Been Love", "Сингл группы Roxette"),
                new TitleDescriptionPair("Itanium", "Микропроцессор")
        );
    }
}
