package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage {
    By SEARCHBUTTON = By.cssSelector(".quick-finder-basic__search-btn");
    By REISETICKETS = By.cssSelector("div.reiseloesung__item");
    By APPLYTICKET = By.cssSelector(".verbindung-list__result-item--0 .reiseloesung-button-container__btn-waehlen");
    By TICKETSLISTHEADER = By.cssSelector(".buchungsstrecke-heading__title");
    By OFFERSSTEP = By.xpath("//*[@data-page=\"Angebote\"]");
    By OFFERCARDS = By.cssSelector(".angebot-details-buttons__auswaehlen");
    By SEATPLACE = By.id("reservierung");
    By CONFIRMOFFER = By.id("btn-weiter");
    By ANONIM = By.xpath("//*[@for='anmeldungauswahl-anonym']");
    By TITLEDD = By.cssSelector(".test-name-anrede");
    By TITLEVALUE = By.cssSelector(".db-web-select-list-item__value");
    By FIRSTNAME = By.xpath("//*[@autocomplete='given-name']");
    By LASTNAME = By.xpath("//*[@autocomplete='family-name']");
    By EMAIL = By.name("kundenkonto-kontakt-email");
    private String url = "https://www.bahn.de/";
    private String kundeUrl = "fahrplan/kundendaten";
    private String offerUrl = "buchung/fahrplan/angebotsauswahl";
    private String paymentUrl = "/buchung/fahrplan/zahlung";


    MainPage mainPage = new MainPage();

    public SearchPage search() {
        $(SEARCHBUTTON).shouldBe(enabled).click();
        validateUrl(url);
        return this;
    }

    public SearchPage validationTableResult(String startpointvalue, String endpointvalue) {
        $$(REISETICKETS).first().shouldHave(Condition.partialText(endpointvalue), Condition.partialText(startpointvalue));
        return this;
    }

    public SearchPage selectionOfTicket(int date, String format) {
        $(APPLYTICKET).shouldBe(enabled).click();
        $(TICKETSLISTHEADER).shouldHave(partialText(mainPage.futureDate(date, format)));
        $(APPLYTICKET).shouldBe(enabled).click();
        $(OFFERSSTEP).shouldBe(visible);
        return this;
    }

    public SearchPage chooseClassOffer() {
        validateUrl(offerUrl);
        waiter = new WebDriverWait(webdriver().object(), Duration.ofSeconds(5));
        $$(OFFERCARDS).first().click();
        $(SEATPLACE).shouldBe(enabled).click();
        $(CONFIRMOFFER).scrollIntoView(false);
        $(CONFIRMOFFER).shouldBe(enabled).click();
        $(ANONIM).shouldBe(visible).click();
        validateUrl(kundeUrl);
        $(CONFIRMOFFER).shouldBe(visible).click();
        return this;
    }

    public SearchPage enterCustomerData(String name, String lastname, String email) {
        waiter = new WebDriverWait(webdriver().object(), Duration.ofSeconds(5));
        $(TITLEDD).shouldBe(enabled).click();
        $$(TITLEVALUE).first().shouldBe(enabled).click();
        $(FIRSTNAME).shouldBe(enabled).setValue(name);
        $(LASTNAME).shouldBe(enabled).sendKeys(lastname);
        $(EMAIL).shouldBe(enabled).sendKeys(email);
        waiter.until(ExpectedConditions.elementToBeClickable(CONFIRMOFFER));
        $(CONFIRMOFFER).click();
        waiter.until(ExpectedConditions.urlContains(paymentUrl));
        return this;
    }

}