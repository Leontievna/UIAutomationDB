package pages;

import com.codeborne.selenide.WebDriverRunner;

import static org.junit.Assert.assertTrue;

public class BasePage {
    String currentUrl;

    public void validateUrl(String url){
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains(url));
    }
}
