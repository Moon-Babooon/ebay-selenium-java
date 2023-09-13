package common;

import base.DriverSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class Utilities extends BrowserOptions {
    private JavascriptExecutor js;

    public void scrollIntoView(WebDriver driver, WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void takeScreenshot(WebDriver driver, String name) {
        Date date = new Date();
        String textDate = date.toString().replace(":","-");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(".\\src\\test\\screenshots\\"+name+textDate+".png"));
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
