package testcases;

import common.BrowserOptions;
import common.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.EbayHomePage;
import java.time.Duration;
import java.util.List;

@Test
public class EbayHomeTest extends BrowserOptions {

    private EbayHomePage ebayHomePage;
    @SuppressWarnings("unused")
    private final Utilities utilities = new Utilities();


    @BeforeMethod
    public void launchTest() {
        ebayHomePage = new EbayHomePage(driver);
        ebayHomePage.loadPage();
    }

    @Test(testName = "Header Test")
    public void headerTest() {
        By HEADER_TOP = By.cssSelector("ul#gh-topl");
        By LOGO = By.cssSelector("a#gh-la");
        By MY_EBAY_BUTTON = By.cssSelector("li#gh-eb-My");
        By MY_EBAY_CONTEXT = By.cssSelector("div#gh-eb-My-o");

        driver.manage().window().maximize();

            WebElement logo = driver.findElement(LOGO);
            Assert.assertEquals(logo.getAttribute("href"), "https://www.ebay.com/");
            List<WebElement> headers = driver.findElements(HEADER_TOP);
            new WebDriverWait(driver, Duration.ofSeconds(5L))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(HEADER_TOP));
            for (WebElement element : headers) {
                String elementName = element.getText();
                boolean isPresent = element.isEnabled();
                Assert.assertTrue(isPresent, elementName + " not found");
            }
            WebElement myEbayBtn = driver.findElement(MY_EBAY_BUTTON);
            ebayHomePage.moveToElement(myEbayBtn);
            new WebDriverWait(driver, Duration.ofSeconds(10L))
                    .until(ExpectedConditions.attributeToBe(MY_EBAY_CONTEXT, "style", "display: block;"));
            List<WebElement> myEbayList = driver.findElements(MY_EBAY_CONTEXT);
            for (WebElement element : myEbayList) {
                String elementName = element.getText();
                System.out.println(elementName);
                boolean isPresent = element.isEnabled();
                Assert.assertTrue(isPresent, elementName + " not found");
            }
    }

    @Test(testName = "Search Test")
    public void searchTest() {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");

        driver.manage().window().maximize();
        ebayHomePage.enterSearchValue("MacBook Pro");
        boolean result = ebayHomePage.resultsToBeMoreThan(RESULTS_IMG_LOCATOR,20);
        Assert.assertTrue(result, "Search results do not meet the expectations");
    }

    @Test(testName = "Search By Category")
    public void shopByCategoryTest() {
        By CATEGORY_BUTTON = By.cssSelector("button#gh-shop-a");
        By CATEGORIES_MENU = By.cssSelector("div#gh-sbc-o");

        driver.manage().window().maximize();

        WebElement categoryBtn = driver.findElement(CATEGORY_BUTTON);
        categoryBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(3L))
                .until(ExpectedConditions.attributeToBe(CATEGORY_BUTTON, "aria-expanded", "true"));
        List<WebElement> menuList = driver.findElements(CATEGORIES_MENU);
        for (WebElement element : menuList) {
            String elementName = element.getText();
            boolean isPresent = element.isDisplayed();
            Assert.assertTrue(isPresent,elementName + "not found");
        }
    }

    @Test(testName = "Test of All Categories")
    public void allCategoriesSearch() {
        By SELECT_LOCATOR = By.cssSelector("select#gh-cat");

        driver.manage().window().maximize();

        WebElement dropDownMenu = driver.findElement(SELECT_LOCATOR);

        Select select = new Select(dropDownMenu);
        List<WebElement> menuList = driver.findElements(By.cssSelector("select#gh-cat option"));
        dropDownMenu.click();
        for (WebElement item : menuList) {
            if (menuList.iterator().hasNext()) {
                dropDownMenu.click();
                String itemName = item.getText();
                select.selectByVisibleText(itemName);
            }
        }
        dropDownMenu.click();
    }

    @Test(testName = "Main Menu Test")
    public void contextMenuTest() {
        By CONTEXT_MENU_LOCATOR = By.cssSelector("div.hl-cat-nav__flyout");

        driver.manage().window().maximize();
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> menuElements = ebayHomePage.getContextMenuElements();

        for (WebElement element : menuElements) {
            if (menuElements.iterator().hasNext()) {
                try {
                    ebayHomePage.moveToElement(element);
                    WebElement context = driver.findElement(CONTEXT_MENU_LOCATOR);
                    softAssert.assertTrue(context.isDisplayed(), "Element not found");
                } catch (ElementNotInteractableException e) {
                    System.err.println(e.getMessage());
                }

            }
        }
    }

    @Test(testName = "Daily Deals Test")
    public void dailyDealsTest() {
        By DAILY_DEALS_TITLE = By.cssSelector("div.hl-card-header");
        By DAILY_DEALS_LIST = By.cssSelector("ul.carousel__list li");
        By SEE_ALL_BUTTON = By.cssSelector("div.hl-card-header__seeall>a");
        By DEALS_HEADER = By.cssSelector("div.navigation-desktop>h1");

        WebElement dailyDealsTitle = driver.findElement(DAILY_DEALS_TITLE);
        utilities.scrollIntoView(driver, dailyDealsTitle);
        List<WebElement> dailyList = driver.findElements(DAILY_DEALS_LIST);
        Assert.assertTrue(dailyList.size() >= 6, "Not enough items on the daily list");
        WebElement seeAllButton = driver.findElement(SEE_ALL_BUTTON);
        seeAllButton.click();
        utilities.waitForVisibilityByLocator(driver, 5, DEALS_HEADER);
        WebElement dealsHeader = driver.findElement(DEALS_HEADER);
        Assert.assertEquals(dealsHeader.getText(), "Deals", "Header titles does not match");
    }

    @Test(testName = "Footer Test")
    public void footerTest () {
        driver.manage().window().maximize();

        List<WebElement> footerElements = ebayHomePage.getFooterElements();
        for (WebElement element : footerElements) {
            utilities.waitUntilClickable(driver, 5, element);
            String elementName = element.getText();
            boolean isPresent = element.isEnabled();
            Assert.assertTrue(isPresent, "Element "+elementName+" is not displayed");
        }
        List<WebElement> siteList = ebayHomePage.getSites();
        for (WebElement site : siteList) {
            String siteName = site.getText();
            boolean isPresent = site.isEnabled();
            Assert.assertTrue(isPresent, siteName+" in not present on the page");
        }
    }

}
