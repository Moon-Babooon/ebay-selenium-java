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

public abstract class DriverSetup {

    public WebDriver driver;
    public Properties properties = new Properties();
    public FileReader file;

    protected void setup() throws IOException {
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
        } else {
            throw new RuntimeException();
        }
    }

    protected void driverTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
