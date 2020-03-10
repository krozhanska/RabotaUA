package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CabinetPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestCabinetPage {
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

    @Test(
            dataProvider = "Authentication", dataProviderClass = DataProviderTest.class,
            groups = { "functest", "smoketest" },
            alwaysRun = true)
    public void testCountCV(String sEmail, String sPass, String sName, String NumberCV){
        driver.get(baseUrl);
        MainPage main = new MainPage(driver);
        main.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(sEmail);
        loginPage.enterPassword(sPass);
        loginPage.getLoginButton().click();
        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.toMyMenu();
        cabinetPage.getCv();
        assertTrue(cabinetPage.getMessageNumberCV().contains(NumberCV)) ;
        cabinetPage.toMyMenu();
        cabinetPage.clickLogOut();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonWB.killWD();
    }


}
