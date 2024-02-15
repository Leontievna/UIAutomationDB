package pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class BasePage {
    String currentUrl;
    public WebDriverWait waiter;

    @Step("Validation of URL")
    public void validateUrl(String url) {
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains(url));
    }
}
