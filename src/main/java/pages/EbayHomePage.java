package pages;

import base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utilities;

import java.time.Duration;
import java.util.List;

public class EbayHomePage extends AbstractPage {

    public EbayHomePage(WebDriver driver) {
        // https://www.ebay.com
        super(driver, "https://www.ebay.com");
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

        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.visibilityOfElementLocated(FOOTER_LOCATOR));
        Utilities utilities = new Utilities();
        utilities.scrollToBottom(driver);
        return driver.findElements(FOOTER_LOCATOR);
    }

    public List<WebElement> getSites() {
        By COUNTRY_MENU_BUTTON = By.cssSelector("a#gf-fbtn");
        By COUNTRY_LIST = By.cssSelector("div#gf-f");

        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.elementToBeClickable(COUNTRY_MENU_BUTTON));
        moveToElement(driver.findElement(COUNTRY_MENU_BUTTON));
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.attributeToBe(COUNTRY_MENU_BUTTON,"aria-expanded", "true"));
        return driver.findElements(COUNTRY_LIST);
    }

}
