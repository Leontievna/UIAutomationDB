package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage{
    By SEARCHBUTTON = By.cssSelector(".quick-finder-basic__search-btn");
    By REISETICKETS = By.cssSelector("div.reiseloesung__item");
    By TICKETDATA (String ort) {return By.cssSelector("[@title=\""+ort+" Hbf\"]");}
    By APPLYTICKET =  By.cssSelector(".verbindung-list__result-item--0 .reiseloesung-button-container__btn-waehlen");
    By TICKETSLISTHEADER =  By.cssSelector(".buchungsstrecke-heading__title");
    By OFFERSSTEP =  By.xpath("//*[@data-page=\"Angebote\"]");
    By OFFERCARDS =  By.cssSelector(".angebot-details-buttons__auswaehlen");
    By SEATPLACE =  By.id("reservierung");
    By CONFIRMOFFER =  By.id("btn-weiter");
    private String url = "https://www.bahn.de/buchung/fahrplan/suche";
    private String kundeUrl = "buchung/fahrplan/kundendaten";
    private String offerUrl = "buchung/fahrplan/angebotsauswahl";


    MainPage mainPage = new MainPage();
    public SearchPage search(){
        $(SEARCHBUTTON).shouldBe(enabled).click();
        validateUrl(url);
        return this;
    }

    public SearchPage validationTableResult(String startpointvalue, String endpointvalue){
        $$(REISETICKETS).first().shouldHave(Condition.partialText(endpointvalue), Condition.partialText(startpointvalue));
        return this;
    }

    public SearchPage selectionOfTicket(int date, String format){
        $(APPLYTICKET).shouldBe(enabled).click();
        $(TICKETSLISTHEADER).shouldHave(partialText(mainPage.futureDate(date,format)));
        $(APPLYTICKET).shouldBe(enabled).click();
        $(OFFERSSTEP).shouldBe(visible);
        return this;
    }

    public SearchPage chooseClassOffer(){
        validateUrl(offerUrl);
        $$(OFFERCARDS).first().click();
        $(SEATPLACE).click();
        $(CONFIRMOFFER).shouldBe(visible).click();
        return this;
    }

}
