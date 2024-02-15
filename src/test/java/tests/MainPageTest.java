package tests;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
@Epic("Login")
public class MainPageTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    @Test
    @Description("Smoke test for login")
    public void LoginTest(){
        String userName = "Kunde.karla@gmx.de";
        mainPage.openMainPage()
                .closeCoockiesIfPresent();
        loginPage.login()
                .inputCredentials(userName);
    }
}
