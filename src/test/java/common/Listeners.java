package common;

import org.testng.*;
import addons.Logging;
import org.testng.annotations.Test;
import pages.EbayHomePage;
import testcases.EbayHomeTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Listeners extends BrowserOptions implements ITestListener {

    private final Properties p = new Properties();
    private final FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\driverConfig.properties");
    private EbayHomePage ebayHomePage;
    private EbayHomeTest ebayHomeTest;

    public Listeners() throws FileNotFoundException {
    }


    public void onTestStart(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String testName = test.testName();
        String browser = p.getProperty("browser");
        logging.logInfo(" | "+browser+" | "+testName+" | has started successfuly.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String testName = test.testName();
        String browser = p.getProperty("browser");
        logging.logInfo(" | "+browser+" | "+testName+" | SUCCESS."
                +"\n-----------------------------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ebayHomePage = new EbayHomePage(driver);
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            e.getCause();
        }
        String browser = p.getProperty("browser");
        @SuppressWarnings("unused")
        int status = result.getStatus();
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String testName = test.testName();
        logging.logFatal(" | "+browser+" | "+testName+" | FAILURE."
                +"\n-----------------------------------------------------------------------------------------");
        try {
            Utilities utilities = new Utilities();
            utilities.takeScreenshot(driver, testName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logging logging = new Logging();
        try {
            p.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String testName = test.testName();
        String browser = p.getProperty("browser");
        logging.logError(" | "+browser+" | "+testName+" | SKIP."
                +"\n-----------------------------------------------------------------------------------------");
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
