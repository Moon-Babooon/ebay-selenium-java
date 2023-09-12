package common;

import base.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

public class BrowserOptions extends DriverSetup {

    @BeforeSuite
    public void launchBrowser() throws IOException {
        setDriver();
    }

    @AfterSuite
    public void quitBrowser() {
        quitDriver();
    }

}
