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

public class GoogleAdsTest extends BrowserOptions {

    private EbayHomePage ebayHomePage;
    private final Utilities utilities = new Utilities();

    @BeforeMethod
    public void startTest() {
        ebayHomePage = new EbayHomePage(driver);
        ebayHomePage.loadPage();
    }

    @Test(testName = "Google Ads Test")
    public void googleAdsTest() {
        By RESULTS_IMG_LOCATOR = By.cssSelector("div.s-item__image-wrapper");
        By SPONSORED_TITLE = By.xpath("//div[@id='adBlock']//span[text()='Sponsored']");
        By AD_BLOCK = By.cssSelector("div[aria-label='Ads by Google'] div[data-bg='true']");
        By IFRAME = By.cssSelector("iframe#master-1");
        driver.manage().window().maximize();
        // test script
        ebayHomePage.enterSearchValue("iPad Pro");
        ebayHomePage.resultsToBeMoreThan(RESULTS_IMG_LOCATOR, 20);
        utilities.scrollToBottom(driver);
        utilities.waitForVisibilityByLocator(driver, 10L, IFRAME);
        WebElement iFrame = driver.findElement(IFRAME);
        driver.switchTo().frame(iFrame);
        List<WebElement> sponsoredList =driver.findElements(SPONSORED_TITLE);
        Assert.assertTrue(sponsoredList.size() >= 2, "No sponsored titles found");
        List<WebElement> adsList = driver.findElements(AD_BLOCK);
        for (WebElement adElement : adsList) {
            utilities.scrollIntoView(driver, adElement);
            Assert.assertTrue(adElement.isDisplayed(), "No element found");
            if (adElement.isDisplayed())
                System.out.println(adElement.getText()+"\n");
        }
    }

}
