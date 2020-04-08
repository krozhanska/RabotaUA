package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CabinetPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
@Listeners({CustomTestListener.class})
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
            alwaysRun = true,
            priority = 0,
            description = "Test Description: verify number of CV with following parameters Email : (0), password : (1), number of CV must be : (3) " )

    public void testCountCV(String sEmail, String sPass, String sName, String NumberCV){
        driver.get(baseUrl);
        MainPage main = new MainPage(driver);
        main.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(sEmail);
        loginPage.enterPassword(sPass);
        loginPage.getLoginButton().click();
        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.clickMyMenu();
        cabinetPage.getCv();
        assertTrue(cabinetPage.getMessageNumberCV().contains(NumberCV), "Number of CV not matching ") ;
        cabinetPage.clickMyMenu();
        cabinetPage.clickLogOut();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonWB.killWD();
    }


}
