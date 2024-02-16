package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.BasePage;
import pages.MainPage;
import pages.SearchPage;

@Epic("Ticket search")
public class SearchTest extends BasePage {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @ParameterizedTest
    @CsvFileSource(files = "src/test/java/resources/destination.csv", numLinesToSkip = 1)
    @Description("Smoke test for search")
    public void searchSmokeTest(String startpointvalue, String endpointvalue){
        try{
        mainPage.openMainPage()
                .closeCoockiesIfPresent()
                .enterSearchStartEndPoints(startpointvalue,endpointvalue);
        searchPage.search()
                .validationTableResult(startpointvalue,endpointvalue);
        }
        catch(Throwable error){
            Allure.step(error.getMessage());
            Allure.attachment("screenshot", attachScreenshot());
            throw error;
        }
    }

    @Test
    @Description("Main scenario")
    public void searchE2ETest() {
        try{
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
                .enterSearchStartEndPoints(endpointvalue,startpointvalue)
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
        catch(Throwable error){
            Allure.step(error.getMessage());
            Allure.attachment("screenshot", attachScreenshot());
            throw error;
        }
    }
}
