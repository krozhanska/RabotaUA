package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BrowserSetup;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertTrue;

@Listeners({CustomTestListener.class})
public class TestWrongEmail {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;

    @Parameters({"browserValue"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browserValue)  {
        driver = BrowserSetup.getInstance (browserValue);
        baseUrl = "https://rabota.ua";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "AuthenticationNotValid", dataProviderClass = DataProviderTest.class,
            groups = { "functest"},
            description = "Test Description: verify wrong email with following parameters Email : (0)")

    public void testEmail(String sEmail){
        boolean result;
        step("Open browser");
        driver.get(baseUrl);
        MainPage main = new MainPage(driver);
        step("Open Login form");
        main.setEnterLogin();
        LoginPage loginPage = new LoginPage(driver);
        step("Enter incorrect email");
        loginPage.enterEmail(sEmail);
        step("Switch to the password field");
        loginPage.clickPassword();
        result= loginPage.getNotValidEmailMessage().isDisplayed();
        assertTrue(result, "Not valid message not matching");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown()  {
        BrowserSetup.killWD();
    }
}
