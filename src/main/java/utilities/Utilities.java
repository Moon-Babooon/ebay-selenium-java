package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utilities {
    private JavascriptExecutor js;

    public void scrollIntoView(WebDriver driver, WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void takeScreenshot(WebDriver driver) {
        Date date = new Date();
        String textDate = date.toString().replace(":","-");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(".\\src\\test\\resources\\screenshots\\"+textDate+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
