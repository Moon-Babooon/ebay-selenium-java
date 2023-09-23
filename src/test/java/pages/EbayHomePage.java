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
        // https://www.ebay.com/
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
        WebElement searchInput = driver.findElement(SEARCH_INPUT_LOCATOR);
        searchInput.clear();
        searchInput.sendKeys(searchFor);
    }

    public void executeSearch(boolean pressEnter) {
        By SEARCH_INPUT_LOCATOR = By.cssSelector("input.ui-autocomplete-input");
        By SEARCH_BUTTON_LOCATOR = By.cssSelector("input.btn");
        WebElement searchInput = driver.findElement(SEARCH_INPUT_LOCATOR);
        if (pressEnter) {
            searchInput.sendKeys(Keys.ENTER);
        } else {
            WebElement searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
            searchButton.click();
        }
    }

    public boolean resultsToBeMoreThan(By locator, int resultsCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, resultsCount));
        List<WebElement> result = driver.findElements(locator);
        System.out.println("RESULTS FOUND: "+result.size());
        return result.size() > resultsCount;
    }


}
