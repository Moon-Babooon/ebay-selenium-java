package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Logging;
import utilities.Utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Listeners extends Utilities implements ITestListener {

    private Properties p = new Properties();
    private FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\driverConfig.properties");

    public Listeners() throws FileNotFoundException {
    }

    public void onTestStart(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String testName = result.getName();
        String browser = p.getProperty("browser");
        logging.logInfo(" | "+browser+" | "+testName+" | has started successfuly.");
    }

    public void onTestSuccess(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String testName = result.getName();
        String browser = p.getProperty("browser");
        logging.logInfo(" | "+browser+" | "+testName+" | PASSED."
                +"\n-----------------------------------------------------------------------------------------");
    }

    public void onTestFailure(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int status = result.getStatus();
        String browser = p.getProperty("browser");

        String testName = result.getName();
        logging.logFatal(" | "+browser+" | "+testName+" | FAILED."
                +"\n-----------------------------------------------------------------------------------------");

        //getScreenshot(browser, testName, status);
    }

    public void onTestSkipped(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String testName = result.getName();
        String browser = p.getProperty("browser");
        logging.logError(" | "+browser+" | "+testName+" | SKIPPED."
                +"\n-----------------------------------------------------------------------------------------");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        Logging logging = new Logging();

        String suiteName = context.getName();
        logging.logInfo(" --- Test Suite started execution: "+suiteName+" --- ");
    }

    public void onFinish(ITestContext context) {
        Logging logging = new Logging();

        String suiteName = context.getName();
        logging.logInfo("Test Suite has finished execution : "+suiteName
                +"\n"
                +"========================================================================================="
                +"\n");
    }

}
