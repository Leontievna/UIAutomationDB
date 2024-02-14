package tests;


import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;

public class MainPageTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void LoginTest(){
        String userName = "Kunde.karla@gmx.de";
        mainPage.openMainPage()
                .closeCoockiesIfPresent();
        loginPage.login()
                .inputCredentials(userName);
    }
}
