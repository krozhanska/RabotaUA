package ua.rabota;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

@Listeners({CustomTestListener.class})
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
            groups = { "functest"},
            description = "Test Description: verify wrong email with following parameters Email : (0)")

    public void testEmail(String sEmail)throws Exception {
        boolean result = true;
        driver.get(baseUrl);
        MainPage main = new MainPage(driver);
        main.setEnterLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(sEmail);
        loginPage.clickPassword();
        result= loginPage.getNotValidEmailMessage().isDisplayed();
        assertTrue(result, "Not valid message not matching");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonWB.killWD();
    }
}
