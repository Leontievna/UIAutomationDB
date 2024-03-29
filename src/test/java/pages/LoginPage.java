package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    By LOGINBUTTON = By.id("js-login-nav");
    By USERINPUT = By.id("username");
    By BACKBUTTON = By.id("mobile-back-button-link");
    By ACCESSBUTTON = By.id("kc-login");
    By TITLE = By.cssSelector("#kc-page-title .login-view");
    private String mainUrl = "https://www.bahn.de/";
    private String url = "https://accounts.bahn.de/auth/";

    @Step("Click a login button")
    public LoginPage login() {
        $(LOGINBUTTON).shouldBe(Condition.enabled).click();
        validateUrl(url);
        $(TITLE).shouldBe(Condition.visible);
        return new LoginPage();
    }

    @Step("Enter a username for login")
    public MainPage inputCredentials(String username) {
        $(USERINPUT).sendKeys(username);
        $(ACCESSBUTTON).click();
        $(BACKBUTTON).click();
        return new MainPage();
    }

}
