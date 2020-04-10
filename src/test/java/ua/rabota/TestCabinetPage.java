package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CabinetPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
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
        step("Open browser");
        MainPage main = new MainPage(driver);
        main.openLoginForm();
        step("Open Login form");
        LoginPage loginPage = new LoginPage(driver);
        step("Enter Email {0}");
        loginPage.enterEmail(sEmail);
        step("Enter password {0}");
        loginPage.enterPassword(sPass);
        step("Click Log in button");
        loginPage.getLoginButton().click();
        CabinetPage cabinetPage = new CabinetPage(driver);
        step("Click My menu button");
        cabinetPage.clickMyMenu();
        step("Click My CV menu");
        cabinetPage.getCv();
        assertTrue(cabinetPage.getMessageNumberCV().contains(NumberCV), "Number of CV not matching ") ;
        step("Click My menu button");
        cabinetPage.clickMyMenu();
        step("Click Log out button");
        cabinetPage.clickLogOut();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonWB.killWD();
    }


}
