package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class AbstractPage extends DriverSetup {

    private final String url;

    public AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }
    public void loadPage() {
        driver.get("about:blank");
        driver.get(url);
        }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveToElementByOffset(WebElement element, int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element, x, y).perform();
    }

    public void moveToElementLocated(int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveToLocation(x, y).perform();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveByOffset(int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).perform();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
