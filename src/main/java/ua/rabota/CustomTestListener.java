package ua.rabota;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethodListener;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.BrowserSetup;

/*import java.util.logging.Logger;*/

public class CustomTestListener extends BrowserSetup implements ITestListener, ISuiteListener, IInvokedMethodListener {
/*public class CustomTestListener extends TestListenerAdapter {*/
    private Logger log = (Logger) LoggerFactory.getLogger(CustomTestListener.class);


    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test class started: " + result.getTestClass().getName());
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        /*makeScreenshot();*/
        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
        /*Object currentClass = result.getInstance();
        WebDriver driver = ((BrowserSetup) currentClass).getDriver();

        if (driver != null)
        {makeScreenshot(driver); }*/
        Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
        makeScreenshot((WebDriver) webDriverAttribute);

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
