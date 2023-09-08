package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DriverSetup {

    public WebDriver driver;
    public static Properties properties = new Properties();
    public static FileReader file;

    public void launchBrowser() throws IOException {
        try {
            browserSetup();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            // log an error

        }

    }

    public void quitDriver() {
        driverTearDown();
    }

    private void browserSetup() throws IOException {
        if (this.driver == null){
            file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\driverConfig.properties");
            properties.load(file);
        }
        if (properties.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            this.driver = new SafariDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("ie")) {
            this.driver = new InternetExplorerDriver();
        }
    }

    private void driverTearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
