package testcases.cucumber;

import base.WebDriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.EbayHomePage;
import testcases.cucumber.steps.WebSteps;
import utils.DataHolder;

@CucumberOptions(features = "src/test/java/testcases/cucumber/features/",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeSuite
    public static void launchBrowser() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        WebSteps.ebayHomePage = new EbayHomePage(driver);
    }

    @AfterSuite
    public static void closeBrowser() {
        DataHolder.getInstance().reset();
        driver.quit();
    }

}
