package base;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverFactory {

    public static WebDriver driver;

    @SneakyThrows
    public static WebDriver getDriver(){
        String type = System.getProperty("driver.type", "CHROME_REMOTE");
        BrowserType browserType = BrowserType.valueOf(type);
        switch (browserType) {
            case DOCKER:
                return initDockerChromeDriver();
            case DOCKER_FIREFOX:
                return initDockerFirefoxDriver();
            case REMOTE_CHROME:
                return initRemoteChromeDriver();
            case REMOTE_FIREFOX:
                return initRemoteFirefoxDriver();
            case LOCAL_FIREFOX:
                return initLocalFirefoxDriver();
            case LOCAL_CHROME:
                return initLocalChromeDriver();
            default:
                System.err.println("DRIVER ERROR");
        }
        throw new RuntimeException();
    }

    private static WebDriver initLocalChromeDriver() {
        WebDriver driver = new ChromeDriver(chromeOptions());
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initLocalFirefoxDriver() {
        WebDriver driver = new FirefoxDriver(firefoxOptions());
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initRemoteChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions());
    }

    private static WebDriver initRemoteFirefoxDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions());
    }

    private static WebDriver initDockerChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://selenoid-selenoid-1:4444/wd/hub"), chromeOptions());
    }

    private static WebDriver initDockerFirefoxDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://selenoid-selenoid-1:4444/wd/hub"), firefoxOptions());
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return options;
    }

}
