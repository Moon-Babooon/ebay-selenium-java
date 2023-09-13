package testcases;

import common.BrowserOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EbayHomePage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginTest extends BrowserOptions {

    private EbayHomePage ebayHomePage;
    private FileReader file;
    private Properties properties = new Properties();

    @BeforeMethod
    public void launchTest() {
        ebayHomePage = new EbayHomePage(driver);
        ebayHomePage.loadPage();
    }

    @Test(testName = "Login Test")
    public void loginTest() throws IOException {
        By ACCOUNT_HEADER_BTN = By.cssSelector("button#gh-ug");
        By ACCOUNT_WINDOW = By.cssSelector("div#gh-eb-u-o");
        file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\credentials.properties");
        properties.load(file);
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        ebayHomePage.loginToAccount(username, password);
        // Inside the account
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_HEADER_BTN));
        WebElement myAccountButton = driver.findElement(ACCOUNT_HEADER_BTN);
        ebayHomePage.moveToElement(myAccountButton);
        driverWait.until(ExpectedConditions.attributeToBe(ACCOUNT_HEADER_BTN, "aria-expanded", "true"));
        WebElement accountWindow = driver.findElement(ACCOUNT_WINDOW);
        Assert.assertTrue(accountWindow.isDisplayed(), "Account window is not visible");
    }

}
