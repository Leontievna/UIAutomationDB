package tests;


import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;
@Epic("Login")
public class MainPageTest extends BasePage {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    @Test
    @Description("Smoke test for login")
    public void LoginTest(){
        try{
        String userName = "Kunde.karla@gmx.de";
        mainPage.openMainPage()
                .closeCoockiesIfPresent();
        loginPage.login()
                .inputCredentials(userName);
        }
        catch(Throwable error){
            Allure.step(error.getMessage());
            Allure.attachment("screenshot", attachScreenshot());
            throw error;
        }
    }
}
