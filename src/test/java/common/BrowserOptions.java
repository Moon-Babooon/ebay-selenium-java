package common;

import base.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

public class BrowserOptions extends DriverSetup {

    @BeforeClass
    public void launchBrowser() throws IOException {
        setDriver();
    }

    @AfterClass
    public void quitBrowser() {
        quitDriver();
    }

}
