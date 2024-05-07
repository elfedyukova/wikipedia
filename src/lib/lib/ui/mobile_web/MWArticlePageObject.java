package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://span[@class='mw-page-title-main']";
        FOOTER_ELEMENT = "xpath://footer[@role='contentinfo']";
        SAVE_BUTTON = "css:.minerva-icon.minerva-icon--star-base20";
        ARTICLE_TITLE = "css:.mw-page-title-main";
        CANCEL_BUTTON = "id:Отменить";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
