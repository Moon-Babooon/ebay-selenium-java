package utilities;

import base.DriverSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utilities extends DriverSetup {

    private JavascriptExecutor js;

    public void scrollIntoView(WebDriver driver, WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    private String testStatus;
    public void getScreenshot(String browser, String testName, int status) {
        Date date = new Date();
        String textDate = date.toString().replace(" ", "_").replace(":", "-");
        if (status==1)
            this.testStatus = "SUCCESS";
         else if (status==2)
             this.testStatus = "FAILURE";
        else if (status==3)
            this.testStatus = "SKIP";
        String screenshotName = testStatus+" "+testName+" "+browser+" "+textDate;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./src/test/resourses/screenshots/"+browser+"/"+screenshotName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
