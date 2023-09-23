package common;

import base.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static testcases.cucumber.CucumberTestRunner.driver;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

@SuppressWarnings("unused")
public class Utilities extends WebDriverFactory {

    public void takeScreenshot(WebDriver driver, String path) {


        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitForVisibility(WebDriver driver, long seconds, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityByLocator(WebDriver driver, long seconds, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilClickable(WebDriver driver, long seconds, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilClickableByLocator(WebDriver driver, long seconds, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

}
