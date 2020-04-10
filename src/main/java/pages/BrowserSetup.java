package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserSetup {
    private static WebDriver driver;
    public WebDriver getDriver()
    {
        return driver;
    }
    public static WebDriver getInstance (String browser){

            if (browser.equals("firefox")){
                System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
                driver =new FirefoxDriver();
            } else if (browser.equals("chrome")){
                System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new UnsupportedOperationException("Unknown browser" +browser);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
    public static void killWD(){
        driver.quit();
        driver = null;
    }
}
