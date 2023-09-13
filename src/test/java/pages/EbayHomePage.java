package pages;

import addons.Logging;
import base.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.Utilities;

import java.time.Duration;
import java.util.List;

@SuppressWarnings("unused")
public class EbayHomePage extends AbstractPage {

    private Utilities utilities = new Utilities();
    private Logging logging;

    public EbayHomePage(WebDriver driver) {
        // https://www.ebay.com
        super(driver, "https://www.ebay.com");
    }

    public void loginToAccount(String username, String password) {
        By SIGN_IN_BUTTON = By.xpath("//a[text()='Sign in']");
        By USERNAME_INPUT_FIELD = By.cssSelector("input#userid");
        By PASSWORD_INPUT_FIELD = By.cssSelector("input#pass");
        By SIGNIN_CONTINUE_BUTTON = By.cssSelector("button#signin-continue-btn");
        By FINAL_SIGNIN_BUTTON = By.cssSelector("button#sgnBt");
        By TIRED_OF_PASSWORDS = By.cssSelector("main#mainContent");
        By DONT_ASK_AGAIN = By.cssSelector("a#dont-ask-again-link");
        By H_CAPTCHA = By.cssSelector("div#checkbox");
        By CAPTCHA_HEADER = By.xpath("//h1[text() ='Please verify yourself to continue']");
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        // Home Page
        driverWait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        WebElement signIn = driver.findElement(SIGN_IN_BUTTON);
        signIn.click();
        // Username input
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT_FIELD));
        WebElement usernameInput = driver.findElement(USERNAME_INPUT_FIELD);
        usernameInput.sendKeys(username);
        WebElement continueButton = driver.findElement(SIGNIN_CONTINUE_BUTTON);
        continueButton.click();
        // Password input
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_FIELD));
        WebElement passInput = driver.findElement(PASSWORD_INPUT_FIELD);
        passInput.sendKeys(password);
        WebElement signInButton = driver.findElement(FINAL_SIGNIN_BUTTON);
        driverWait.until(ExpectedConditions.elementToBeClickable(FINAL_SIGNIN_BUTTON));
        signInButton.click();
    }

    public void enterSearchValue(String searchFor) {
        By SEARCH_INPUT_LOCATOR = By.cssSelector("input.ui-autocomplete-input");
        By SEARCH_BUTTON_LOCATOR = By.cssSelector("input.btn");

        WebElement searchInput = driver.findElement(SEARCH_INPUT_LOCATOR);
        searchInput.clear();
        searchInput.sendKeys(searchFor);
        WebElement searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchButton.click();
    }

    public boolean resultsToBeMoreThan(int resultsCount) {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");

        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(RESULTS_IMG_LOCATOR, resultsCount));
        List<WebElement> result = driver.findElements(RESULTS_IMG_LOCATOR);
        return result.size() > resultsCount;
    }

    public List<WebElement> getContextMenuElements() {
        By MENU_LIST_LOCATOR = By.cssSelector("ul.hl-cat-nav__container");
        By MENU_ELEMENTS_LOCATOR = By.cssSelector("li.hl-cat-nav__js-tab");

        WebElement menuList = driver.findElement(MENU_LIST_LOCATOR);
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.elementToBeClickable(menuList));
        return driver.findElements(MENU_ELEMENTS_LOCATOR);
    }

    public List<WebElement> getFooterElements() {
        By FOOTER_LOCATOR = By.cssSelector("footer#glbfooter");
        Utilities utilities = new Utilities();

        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.visibilityOfElementLocated(FOOTER_LOCATOR));
        utilities.scrollToBottom(driver);
        return driver.findElements(FOOTER_LOCATOR);
    }

    public List<WebElement> getSites() {
        By COUNTRY_MENU_BUTTON = By.cssSelector("a#gf-fbtn");
        By COUNTRY_LIST = By.cssSelector("div#gf-f");

        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.elementToBeClickable(COUNTRY_MENU_BUTTON));
        WebElement countryMenuBtn = driver.findElement(COUNTRY_MENU_BUTTON);
        moveToElement(countryMenuBtn);
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.attributeToBe(COUNTRY_MENU_BUTTON,"aria-expanded", "true"));
        return driver.findElements(COUNTRY_LIST);
    }

}
