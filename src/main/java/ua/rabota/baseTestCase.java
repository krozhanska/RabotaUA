package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;

public  class baseTestCase {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

   /* @BeforeMethod
    public void createDriver() {
        Webdriver driver=XXXXDriver();
    }*/

    @AfterMethod
    public void tearDownDriver() {
        if (driver != null)
        {
            try
            {
                driver.quit();
            }
            catch (WebDriverException e) {
                System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
                System.out.println(e);
            }

        }
    }
}
