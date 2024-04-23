package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java";
        FOOTER_ELEMENT = "id:Просмотреть статью в браузере";
        SAVE_BUTTON = "id:Сохранить на потом";
        NAVIGATE_UP_BUTTON = "id:Назад";
        ARTICLE_TITLE = "id:Java";
        CANCEL_BUTTON = "id:Отменить";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}