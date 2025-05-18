package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends BasePage {
    By SEARCHBUTTON = By.cssSelector(".quick-finder-basic__search-btn");
    By REISETICKETS = By.cssSelector("div.reiseloesung__item");
    By APPLYTICKET = By.cssSelector(".verbindung-list__result-item--0 .reiseloesung-button-container__btn-waehlen");
    By TICKETSLISTHEADER = By.cssSelector(".buchungsstrecke-heading__title");
    By OFFERSSTEP = By.xpath("//*[@data-page=\"Angebote\"]");
    By OFFERCARDSBUTTON = By.xpath("//button[.='Auswählen']");
    By SEATPLACEDESCRIPTION = By.cssSelector("#reservierung .db-web-checkbox__input");
    By SEATPLACE = By.className("platzreservierung-label");
    By CONFIRMOFFER = By.id("btn-weiter");
    By ANONIM = By.xpath("//*[@for='anmeldungauswahl-anonym']");
    By TITLEDD = By.cssSelector(".test-name-anrede");
    By TITLEVALUE = By.cssSelector("li[data-value=\"HR\"]");
    By FIRSTNAME = By.xpath("//*[@autocomplete='given-name']");
    By LASTNAME = By.xpath("//*[@autocomplete='family-name']");
    By EMAIL = By.name("kundenkonto-kontakt-email");
    MainPage mainPage = new MainPage();
    private String url = "https://www.bahn.de/";
    private String offerUrl = "buchung/fahrplan/angebotsauswahl";
    private String paymentUrl = "/buchung/fahrplan/zahlung";

    @Step("Click search button")
    public SearchPage search() {
        $(SEARCHBUTTON).shouldBe(enabled).click();
        validateUrl(url);
        return this;
    }

    @Step("Check the table with results of search")
    public SearchPage validationTableResult(String startpointvalue, String endpointvalue) {
        $$(REISETICKETS).first().shouldBe(visible,Duration.ofSeconds(6))
                .shouldHave(Condition.partialText(endpointvalue), Condition.partialText(startpointvalue));
        return this;
    }

    @Step("Selection of tickets")
    public SearchPage selectionOfTicket(int date, String format) {
        $(APPLYTICKET).shouldBe(enabled).click();
        $(TICKETSLISTHEADER).shouldHave(partialText(mainPage.futureDate(date, format)));
        $(APPLYTICKET).shouldBe(enabled).click();
        $(OFFERSSTEP).shouldBe(visible);
        return this;
    }

    @Step("Choose a class of the trip and place")
    public SearchPage chooseClassOffer() {
        validateUrl(offerUrl);
        waiter = new WebDriverWait(webdriver().object(), Duration.ofSeconds(12));
        //$$(OFFERCARDS).first().click();
        if ($$(OFFERCARDSBUTTON).first().isEnabled()){
            $$(OFFERCARDSBUTTON).first().click();
        }
        $(SEATPLACE).scrollIntoView(true);
        //waiter.until(ExpectedConditions.visibilityOf($(SEATPLACE)));
        $(SEATPLACE).shouldBe(enabled,Duration.ofSeconds(6)).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(SEATPLACEDESCRIPTION));
        $(CONFIRMOFFER).scrollIntoView(true);
        $(CONFIRMOFFER).shouldBe(visible, Duration.ofSeconds(10)).click();
        $(ANONIM).shouldBe(visible, Duration.ofSeconds(8));
        Allure.addAttachment("Test", attachScreenshot());
        return this;
    }

    @Step("Enter of a passenger data")
    public SearchPage enterCustomerData(String name, String lastname, String email) {
        $(ANONIM).shouldBe(enabled).click();
        $$(TITLEDD).first().shouldBe(enabled, Duration.ofSeconds(5)).click();
        $(TITLEVALUE).shouldBe(enabled).click();
        $(FIRSTNAME).shouldBe(enabled).setValue(name);
        $(LASTNAME).shouldBe(enabled).sendKeys(lastname);
        $(EMAIL).shouldBe(enabled).sendKeys(email);
        waiter.until(ExpectedConditions.elementToBeClickable(CONFIRMOFFER));
        $(CONFIRMOFFER).click();
        waiter.until(ExpectedConditions.urlContains(paymentUrl));
        return this;
    }

}
