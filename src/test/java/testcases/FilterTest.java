package testcases;

import common.BrowserOptions;
import common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EbayHomePage;

import java.util.List;

public class FilterTest extends BrowserOptions {

    private EbayHomePage ebayHomePage;
    private final Utilities utilities = new Utilities();

    @BeforeMethod
    public void launchTest() {
        ebayHomePage = new EbayHomePage(driver);
        ebayHomePage.loadPage();
    }

    @Test(testName = "Search Filter Test")
    public void searchFilterTest() {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");
        By LEFT_FILTER_SECTION_LOCATOR = By.cssSelector("div.srp-rail__left>ul>li>ul.x-refine__left__nav");
        By FILTER_LIST_LOCATOR = By.cssSelector("div.srp-rail__left>ul>li>ul.x-refine__left__nav>li");
        driver.manage().window().maximize();

        ebayHomePage.enterSearchValue("MacBook Air");
        ebayHomePage.resultsToBeMoreThan(RESULTS_IMG_LOCATOR, 50);
        utilities.waitForVisibilityByLocator(driver, 5L,LEFT_FILTER_SECTION_LOCATOR);
        List<WebElement> filterList = driver.findElements(FILTER_LIST_LOCATOR);
        for (WebElement element : filterList) {
            Assert.assertTrue(element.isEnabled(), "Element "+element.getText()+" is not enabled");
            utilities.waitUntilClickable(driver, 3L, element);
            utilities.scrollIntoView(driver, element);
        }

    }

}
