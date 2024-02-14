package tests;

import org.junit.Test;
import pages.MainPage;
import pages.SearchPage;

public class SearchTest {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();


    @Test
    public void FirstTest(){
        String startpointvalue = "Bonn";
        String endpointvalue = "Hamburg";
        mainPage.openMainPage()
                .closeCoockiesIfPresent()
                .enterSearchStartEndPoints(startpointvalue,endpointvalue);
        searchPage.search()
                .validationTableResult(startpointvalue,endpointvalue);
    }

    @Test()
    public void SecondTest() {
        String startpointvalue = "Bonn";
        String endpointvalue = "Hamburg";
        String format = "dd.MM.yyyy";
        String formatCheck = "dd. MMM";
        String name = "Anton";
        String lastname = "Dolin";
        String userEmail = "Kunde.karla@gmx.de";

        int date1 = 2;
        int date2 = date1 + 3;
        mainPage.openMainPage()
                .closeCoockiesIfPresent()
                .enterSearchStartEndPoints(startpointvalue,endpointvalue)
                .chooseStartDate(date1, format)
                .chooseReturnDate(date2, format)
                .addPassenger()
                .checkSelectedParameters(date1,date2,3, formatCheck);
        searchPage.search()
                .validationTableResult(startpointvalue, endpointvalue)
                .selectionOfTicket(date2,formatCheck)
                .chooseClassOffer()
                .enterCustomerData(name, lastname, userEmail);
    }
}
