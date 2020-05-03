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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*import java.util.logging.Logger;*/

public class CustomTestListener extends BrowserSetup implements ITestListener, ISuiteListener, IInvokedMethodListener {

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

        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
        Class clazz = result.getTestClass().getRealClass();
        Field field = null;
        try {
            field = clazz.getDeclaredField("driver");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        field.setAccessible(true);

        WebDriver driver1 = null;
        try {
            driver1 = (WebDriver) field.get(result.getInstance());

            File scrFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);

            // the filename is the folder name on test.screenshot.path property plus the completeTestName
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String methodName = result.getName();
            String path = "./target/screenshots/"+methodName +"_"+format.format(calendar.getTime())+".png";
            File fileOutput  = new File (path);
            org.apache.commons.io.FileUtils.copyFile(scrFile, fileOutput);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        if (driver1!= null) makeScreenshot(driver1);


    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    }
