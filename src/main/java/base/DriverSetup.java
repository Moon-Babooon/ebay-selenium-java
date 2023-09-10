package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public abstract class DriverSetup {

    public WebDriver driver;
    public Properties properties = new Properties();
    public FileReader file;

    public void launchBrowser() throws IOException {
        setup();
    }

    public void quitDriver() {
        driverTearDown();
    }

    private void setup() throws IOException {
        if (driver == null){
            file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\driverConfig.properties");
            properties.load(file);
        }
        if (properties.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        }
    }

    private void driverTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
