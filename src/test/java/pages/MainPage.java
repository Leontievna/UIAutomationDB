package pages;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByShadow;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

    By STARTPOINTINPUT = By.xpath("//*[contains(@name,'quickFinderBasic-von')]");
    By CLEARICONSTART = By.cssSelector(".test-von-halt .icon-clear");
    By CLEARICONFINISH = By.cssSelector(".test-nach-halt .icon-clear");
    By ENDPOINTINPUT = By.name("quickFinderBasic-nach");
    private String HOSTOFSHADOWELEMENT = "body > div:nth-child(1)";
    private String TARGETOFSHADOWELEMENT = ".js-accept-all-cookies";
    By CALENDAR = By.cssSelector(".button-overlay-body-container__body-content");
    By DATAENTERFIELD = By.cssSelector(".db-web-date-picker-input__field");
    By ACCEPTBUTTON = By.xpath("//*[@data-test-id ='quick-finder-save-button']");
    By STARTDATE = By.cssSelector(".quick-finder-options__hinfahrt");
    By FINISHDATE = By.cssSelector(".quick-finder-options__rueckfahrt-container");
    By PASSANGERSDATA = By.xpath("//*[@data-test-id=\"qf-reisende\"]");
    By UPDATEPASSENGERFORM = By.cssSelector(".button-overlay-body-container__body");
    By DDPASSENGERNUMBER = By.id("reisendeAnzahl-0");
    By CHOOSEPASSANGERNUMBER = By.xpath("//*[@id='reisendeAnzahl-0-list']//*[@data-value = '2']");
    By ADDNEWPASSANGERTYPEDD = By.cssSelector("#reisendeTyp-1");
    By ADDNEWPASSANGER = By.cssSelector(".ReisendeHinzufuegenButton");
    By ADDNEWPASSANGERTYPE = By.xpath("//*[@id='reisendeTyp-1-list']//*[@data-value = 'HUND']");
    By SAVEADDPASSANGERBUTTON = By.xpath("//*[@data-test-id='quick-finder-save-button']");
    private String mainUrl = "https://www.bahn.de/";

    private By POINTDDLIST(String ort) {
        return By.xpath("//*[contains(@data-value, '" + ort + " Hbf')]");
    }

    @Step("Open main page")
    public MainPage openMainPage() {
        open(mainUrl);
        waiter = new WebDriverWait(webdriver().object(), Duration.ofSeconds(5));
        validateUrl(mainUrl);
        return this;
    }

    @Step("Close coockies")
    public MainPage closeCoockiesIfPresent() {
        try {
            waiter.until(ExpectedConditions.elementToBeClickable(ByShadow.cssSelector(TARGETOFSHADOWELEMENT, HOSTOFSHADOWELEMENT)));
            SelenideElement element = $(ByShadow.cssSelector(TARGETOFSHADOWELEMENT, HOSTOFSHADOWELEMENT));
            element.click();
        } catch (TimeoutException a) {
            System.out.println(" ======= No coockie dialog");
        }
        return this;
    }

    @Step("Enter place of start {startpoint} and place of finish {endpoint}")
    public MainPage enterSearchStartEndPoints(String startpoint, String endpoint) {
        if ($(CLEARICONSTART).is(visible)) {
            $(CLEARICONSTART).click();
        }
        $(STARTPOINTINPUT).sendKeys(endpoint);
        $(POINTDDLIST(endpoint)).shouldBe(enabled).click();
        if ($(CLEARICONFINISH).is(visible)) {
            $(CLEARICONFINISH).click();
        }
        $(ENDPOINTINPUT).shouldBe(enabled).sendKeys(startpoint);
        $(POINTDDLIST(startpoint)).shouldBe(enabled).click();
        return this;
    }

    public String futureDate(int date, String format) {
        LocalDate localDate = LocalDate.now();
        LocalDate futureDate = localDate.plusDays(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.GERMAN);
        return futureDate.format(formatter);
    }

    @Step("Choose start date")
    public MainPage chooseStartDate(int date, String format) {
        $(STARTDATE).click();
        $(CALENDAR).shouldBe(visible);
        $(DATAENTERFIELD).shouldBe(enabled).doubleClick();
        $(DATAENTERFIELD).sendKeys(futureDate(date, format));
        $(ACCEPTBUTTON).click();
        return this;
    }

    @Step("Choose finish date")
    public MainPage chooseReturnDate(int date, String format) {
        $(FINISHDATE).click();
        $(CALENDAR).shouldBe(visible);
        $(DATAENTERFIELD).shouldBe(enabled).click();
        $(DATAENTERFIELD).sendKeys(futureDate(date, format));
        $(ACCEPTBUTTON).click();
        return this;
    }

    @Step("Add passenger and a dog")
    public MainPage addPassenger() {
        $(PASSANGERSDATA).shouldBe(enabled).click();
        $(UPDATEPASSENGERFORM).shouldBe(visible);
        $(DDPASSENGERNUMBER).shouldBe(enabled).click();
        $(CHOOSEPASSANGERNUMBER).shouldBe(enabled).click();
        $(ADDNEWPASSANGER).shouldBe(enabled).click();
        $(ADDNEWPASSANGERTYPEDD).shouldBe(enabled).click();
        $(ADDNEWPASSANGERTYPE).shouldBe(enabled).click();
        $(SAVEADDPASSANGERBUTTON).shouldBe(enabled).click();
        return this;
    }

    @Step("Check selected early parameters")
    public MainPage checkSelectedParameters(int dateStart, int dateFinish, int passengers, String format) {
        String startDate = futureDate(dateStart, format);
        String finishDate = futureDate(dateFinish, format);
        $(STARTDATE).shouldHave(partialText(startDate));
        $(FINISHDATE).shouldHave(partialText(finishDate));
        $(PASSANGERSDATA).shouldHave(partialText(String.valueOf(passengers)));
        return this;
    }
}
