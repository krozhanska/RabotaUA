package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestWrongEmail {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;

    @Parameters({"browserValue"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browserValue) throws Exception {
        driver = SingletonWB.getInstance (browserValue);
        baseUrl = "https://rabota.ua";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "AuthenticationNotValid", dataProviderClass = DataProviderTest.class,
            groups = { "functest"})
    public void testEmail(String sEmail)throws Exception {
        boolean result = true;
        driver.get(baseUrl);
        MainPage main = new MainPage(driver);
        main.setEnterLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(sEmail);
        loginPage.clickPassword();
        if (loginPage.getNotValidEmailMessage().isDisplayed() ) {
            result= true;
        }
        else {result = false;}

        assertTrue(result, "Неверный формат");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonWB.killWD();
    }
}
