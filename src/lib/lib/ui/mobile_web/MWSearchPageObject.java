package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import lib.ui.TitleDescriptionPair;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.List;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:.minerva-icon.minerva-icon--search-base20";
        SEARCH_INPUT = "css:input[class='search mf-icon-search']";
        SEARCH_ELEMENT_ON_MAIN_PAGE_IOS = "xpath://XCUIElementTypeSearchField[@name=\"Поиск по Википедии\"]";
        SEARCH_CANCEL_BUTTON = "xpath://div[contains(@class,'header-action')]//span[contains(@class,'mf-icon mf-icon-close')]";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://li[@title='{SUBSTRING}']//h3[1]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name=\'Ничего не найдено\']";
        SEARCH_RESULT = "xpath://XCUIElementTypeApplication[@name=\"Википедия\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView";
        SEARCH_CLEAR = "css:.mf-icon.mf-icon-clear.mf-icon--small";
        SEARCH_RESULT_SUBSTRING_TITLE_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRINGTITLE}'] | //XCUIElementTypeStaticText[@name='{SUBSTRINGDESCRIPTION}']";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getSearchExpectedText() {
        return "Search Wikipedia";
    }

    @Override
    public String getSearchExpectedArticleText() {
        return "Java (programming language)";
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
