package testcases.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.EbayHomePage;
import utils.DataHolder;

import java.util.List;



public class WebSteps {

    public static EbayHomePage ebayHomePage;
    private final SoftAssert softAssert = new SoftAssert();

    @Given("load the Ebay home page")
    public void loadHomePage() {
        System.out.println("Loading page...");
        ebayHomePage.loadPage();
        ebayHomePage.implicitlyWait(5);
    }

    @And("enter the {string} into the search field")
    public void searchTest(String searchFor) {
        System.out.println("Searching...");
        ebayHomePage.enterSearchValue(searchFor);
    }

    @When("press on the 'search' button")
    public void searchWithButton() {
        System.out.println("Pressing button...");
        ebayHomePage.executeSearch(false);
    }

    @When("press ENTER")
    public void searchWithEnter() {
        System.out.println("Pressing ENTER");
        ebayHomePage.executeSearch(true);
    }

    @Then("verify that search results are not empty")
    public void verifySearchResults() {
        System.out.println("Verifying");
        By PRODUCT_TITLE = By.cssSelector("div.s-item__title");
        By PRODUCT_PRICE = By.cssSelector("span.s-item__price");
        List<WebElement> titles = ebayHomePage.getElements(PRODUCT_TITLE);
        List<WebElement> prices = ebayHomePage.getElements(PRODUCT_PRICE);
        Assert.assertTrue(titles.size() > 30 && prices.size() > 30, "Results are not expected");
    }

    @When("verify header navigation")
    public void verifyHeaderElements() {
        By HEADER_TOP = By.cssSelector("ul#gh-topl");
        List<WebElement> headers = ebayHomePage.getElements(HEADER_TOP);
        for (WebElement element : headers) {
            softAssert.assertTrue(element.isEnabled(), element.getText() + " not enabled");
        }
    }

    @Then("verify the 'myEbay' drop down button")
    public void verifyMyEbayButton() {
        By MY_EBAY_BUTTON = By.cssSelector("li#gh-eb-My");
        By MY_EBAY_CONTEXT = By.cssSelector("div#gh-eb-My-o");
        WebElement myEbayBtn = ebayHomePage.getElement(MY_EBAY_BUTTON);
        ebayHomePage.moveToElement(myEbayBtn);
        ebayHomePage.waitForAttributeToBe(MY_EBAY_CONTEXT, "style", "display: block;", 5L);
        List<WebElement> myEbayList = ebayHomePage.getElements(MY_EBAY_CONTEXT);
        for (WebElement element : myEbayList) {
            softAssert.assertTrue(element.isEnabled(), element.getText() + " not enabled!");
        }
    }

    @And("verify logo has a link to the home page: {string}")
    public void verifyLogo(String link) {
        By LOGO = By.cssSelector("a#gh-la");
        WebElement logo = ebayHomePage.getElement(LOGO);
        Assert.assertEquals(logo.getAttribute("href"), link, "Links do not match");
    }

    @When("click on the 'Shop by category' button")
    public void shopByCategoryButtonClick() {
        By CATEGORY_BUTTON = By.cssSelector("button#gh-shop-a");
        WebElement categoryBtn = ebayHomePage.getElement(CATEGORY_BUTTON);
        categoryBtn.click();
        ebayHomePage.waitForAttributeToBe(CATEGORY_BUTTON, "aria-expanded", "true", 10L);
    }

    @Then("verify the elements from category list")
    public void shopByCategoryListVerification() {
        By CATEGORIES_MENU = By.cssSelector("div#gh-sbc-o");
        List<WebElement> menuList = ebayHomePage.getElements(CATEGORIES_MENU);
        for (WebElement element : menuList) {
            softAssert.assertTrue(element.isEnabled(), element.getText() + " not found");
        }
    }

    @When("click on the 'All categories' drop down button")
    public void allCategoriesDropDownAction() {
        By SELECT_LOCATOR = By.cssSelector("select#gh-cat");
        WebElement dropDownMenu = ebayHomePage.getElement(SELECT_LOCATOR);
        dropDownMenu.click();
    }

    @Then("verify the list of categories is not empty")
    public void allCategoriesListVerification() {
        By SELECT_LOCATOR = By.cssSelector("select#gh-cat");
        By MENU_LIST = By.cssSelector("select#gh-cat option");
        WebElement dropDownMenu = ebayHomePage.getElement(SELECT_LOCATOR);
        Select select = new Select(dropDownMenu);
        List<WebElement> menuList = ebayHomePage.getElements(MENU_LIST);
        for (WebElement item : menuList) {
            if (menuList.iterator().hasNext()) {
                dropDownMenu.click();
                select.selectByVisibleText(item.getText());
            }
        }
        dropDownMenu.click();
    }

    @Then("hover over every element and verify the categories are not empty")
    public void contextMenuTest() {
        By MENU_LIST_LOCATOR = By.cssSelector("ul.hl-cat-nav__container");
        By MENU_ELEMENTS_LOCATOR = By.cssSelector("li.hl-cat-nav__js-tab");
        By CONTEXT_MENU_LOCATOR = By.cssSelector("div.hl-cat-nav__flyout");
        ebayHomePage.waitUntilVisible(MENU_LIST_LOCATOR, 10L);
        List<WebElement> menuElements = ebayHomePage.getElements(MENU_ELEMENTS_LOCATOR);
        int lastElement = menuElements.size() - 1;
        menuElements.remove(lastElement);
        for (WebElement element : menuElements) {
            if (menuElements.iterator().hasNext()) {
                try {
                    ebayHomePage.moveToElement(element);
                    WebElement context = ebayHomePage.getElement(CONTEXT_MENU_LOCATOR);
                    softAssert.assertTrue(context.isDisplayed(), context.getText() + " not found");
                } catch (ElementNotInteractableException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Given("scroll to 'Daily Deals' section")
    public void scrollToDailyDeals() {
        By DAILY_DEALS_TITLE = By.cssSelector("div.hl-card-header");
        WebElement dailyDealsTitle = ebayHomePage.getElement(DAILY_DEALS_TITLE);
        ebayHomePage.scrollIntoView(dailyDealsTitle);
    }

    @Then("get the list of products and verify it's not empty")
    public void getDailyDealsList() {
        By DAILY_DEALS_LIST = By.cssSelector("ul.carousel__list li");
        List<WebElement> dailyList = ebayHomePage.getElements(DAILY_DEALS_LIST);
        Assert.assertTrue(dailyList.size() >= 6, "Not enough items on the daily list");
    }

    @And("click on the 'See all' button")
    public void clickSeeAll() {
        By SEE_ALL_BUTTON = By.cssSelector("div.hl-card-header__seeall>a");
        WebElement seeAllButton = ebayHomePage.getElement(SEE_ALL_BUTTON);
        seeAllButton.click();
    }

    @Then("verify the header {string} is visible")
    public void headerVerification(String header) {
        By DEALS_HEADER = By.cssSelector("div.navigation-desktop>h1");
        ebayHomePage.waitUntilVisible(DEALS_HEADER, 10L);
        WebElement dealsHeader = ebayHomePage.getElement(DEALS_HEADER);
        Assert.assertEquals(dealsHeader.getText(), header, "Header title does not match");
    }

    @Given("scroll to bottom of the page")
    public void scrollToFooter() {
        ebayHomePage.scrollToBottom();
    }

    @Then("verify the content of the footer")
    public void footerElementsVerification() {
        By FOOTER_LOCATOR = By.cssSelector("footer#glbfooter");
        ebayHomePage.waitUntilVisible(FOOTER_LOCATOR, 10L);
        List<WebElement> footerElements = ebayHomePage.getElements(FOOTER_LOCATOR);
        for (WebElement element : footerElements) {
            ebayHomePage.waitForElementToBeClickable(element, 5L);
            softAssert.assertTrue(element.isEnabled(), "Element "+element.getText()+" is not displayed");
        }
    }

    @And("verify the list of sites")
    public void listOfSitesVerification() {
        By COUNTRY_MENU_BUTTON = By.cssSelector("a#gf-fbtn");
        By COUNTRY_LIST = By.cssSelector("div#gf-f");
        ebayHomePage.waitForElementToBeClickable(COUNTRY_MENU_BUTTON, 5L);
        WebElement countryMenuBtn = ebayHomePage.getElement(COUNTRY_MENU_BUTTON);
        ebayHomePage.moveToElement(countryMenuBtn);
        ebayHomePage.waitForAttributeToBe(COUNTRY_MENU_BUTTON, "aria-expanded", "true", 5L);
        List<WebElement> siteList = ebayHomePage.getElements(COUNTRY_LIST);
        for (WebElement site : siteList) {
            softAssert.assertTrue(site.isEnabled(), site.getText()+" in not present on the page");
        }
    }

    @Then("scroll down and verify the functionality of Filter")
    public void filterFlowVerification() {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");
        By LEFT_FILTER_SECTION_LOCATOR = By.cssSelector("div.srp-rail__left>ul>li>ul.x-refine__left__nav");
        By FILTER_LIST_LOCATOR = By.cssSelector("div.srp-rail__left>ul>li>ul.x-refine__left__nav>li");
        ebayHomePage.resultsToBeMoreThan(RESULTS_IMG_LOCATOR, 50);
        ebayHomePage.waitUntilVisible(LEFT_FILTER_SECTION_LOCATOR, 10L);
        List<WebElement> filterList = ebayHomePage.getElements(FILTER_LIST_LOCATOR);
        for (WebElement element : filterList) {
            Assert.assertTrue(element.isEnabled(), "Element "+element.getText()+" is not enabled");
            ebayHomePage.waitForElementToBeClickable(element, 5L);
            ebayHomePage.scrollIntoView(element);
        }
    }

    @Then("scroll to bottom and verify that the ads section is not empty")
    public void googleAdsVerification() {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");
        By SPONSORED_TITLE = By.xpath("//div[@id='adBlock']//span[text()='Sponsored']");
        By AD_BLOCK = By.cssSelector("div[aria-label='Ads by Google'] div[data-bg='true']");
        By IFRAME = By.cssSelector("iframe#master-1");
        ebayHomePage.resultsToBeMoreThan(RESULTS_IMG_LOCATOR, 60);
        ebayHomePage.scrollToBottom();
        ebayHomePage.waitUntilVisible(IFRAME, 15L);
        WebElement iFrame = ebayHomePage.getElement(IFRAME);
        ebayHomePage.switchToFrame(iFrame);
        List<WebElement> sponsoredList =ebayHomePage.getElements(SPONSORED_TITLE);
        Assert.assertTrue(sponsoredList.size() >= 2, "No sponsored titles found");
        List<WebElement> adsList = ebayHomePage.getElements(AD_BLOCK);
        for (WebElement adElement : adsList) {
            ebayHomePage.scrollIntoView(adElement);
            Assert.assertTrue(adElement.isDisplayed(), "No element found");
            if (adElement.isDisplayed())
                System.out.println(adElement.getText()+"\n");
        }
    }

    @Given("press on the 'Sign in' link")
    public void startSignIn() {
        By SIGN_IN_LINK = By.xpath("//a[text()='Sign in']");
        ebayHomePage.getElement(SIGN_IN_LINK).click();
    }

    @Then("enter the {string} into a username field and press 'Continue' button")
    public void enterUsername(String username) {
        By USERNAME_INPUT_FIELD = By.cssSelector("input#userid");
        By SIGNIN_CONTINUE_BUTTON = By.cssSelector("button#signin-continue-btn");
        ebayHomePage.waitUntilVisible(USERNAME_INPUT_FIELD, 5L);
        WebElement usernameInput = ebayHomePage.getElement(USERNAME_INPUT_FIELD);
        usernameInput.sendKeys(DataHolder.getInstance().get(username).toString());
        ebayHomePage.getElement(SIGNIN_CONTINUE_BUTTON).click();
    }

    @And("enter the {string} into a password field press on the 'Sign in' button")
    public void enterPassword(String password) {
        By PASSWORD_INPUT_FIELD = By.cssSelector("input#pass");
        By FINAL_SIGNIN_BUTTON = By.cssSelector("button#sgnBt");
        ebayHomePage.waitUntilVisible(PASSWORD_INPUT_FIELD, 5L);
        WebElement passInput = ebayHomePage.getElement(PASSWORD_INPUT_FIELD);
        passInput.sendKeys(DataHolder.getInstance().get(password).toString());
        ebayHomePage.waitForElementToBeClickable(FINAL_SIGNIN_BUTTON, 2L);
        ebayHomePage.getElement(FINAL_SIGNIN_BUTTON).click();
    }

    @Then("verify the successful login in the header section")
    public void verifyLogin() {
        By ACCOUNT_HEADER_BTN = By.cssSelector("button#gh-ug");
        By ACCOUNT_WINDOW = By.cssSelector("div#gh-eb-u-o");
        ebayHomePage.waitUntilVisible(ACCOUNT_HEADER_BTN, 5L);
        WebElement myAccountButton = ebayHomePage.getElement(ACCOUNT_HEADER_BTN);
        ebayHomePage.moveToElement(myAccountButton);
        ebayHomePage.waitForAttributeToBe(ACCOUNT_HEADER_BTN, "aria-expanded", "true", 2L);
        WebElement accountWindow = ebayHomePage.getElement(ACCOUNT_WINDOW);
        Assert.assertTrue(accountWindow.isDisplayed(), "Account window is not visible");
    }

}
