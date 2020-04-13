package ua.rabota;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BrowserSetup;
import pages.CabinetPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertTrue;

@Listeners({CustomTestListener.class})
public class TestLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Parameters({"browserValue"})
  @BeforeClass(alwaysRun = true)
  public void setUp(String browserValue)  {
    driver = new BrowserSetup().getInstance (browserValue);
    baseUrl = "https://rabota.ua";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test(groups = { "smoketest" })
  public void testLog()  {
    step("Open browser");
    driver.get(baseUrl);
    MainPage main = new MainPage(driver);
    step("Open Login form");
    main.setEnterLogin();
    LoginPage loginPage = new LoginPage(driver);
    step("Enter Email :emailns@gmail.com");
    loginPage.enterEmail("emailns@gmail.com");
    step("Enter Password :123");
    loginPage.enterPassword("123");
    step("Click Login button");
    loginPage.getLoginButton().click();

    assertTrue(loginPage.getNotValidCredanceMessage().getText().
            contains("Неправильный логин или пароль."));

  }


  @Test(dataProvider = "Authentication", dataProviderClass = DataProviderTest.class,
          groups = { "functest"})
  public void testLoginPositive(String sEmail, String sPass, String sName, String NumberCV){
    step("Open browser");
    driver.get(baseUrl);
    MainPage main = new MainPage(driver);
    step("Open Login form");
    LoginPage loginPage = main.openLoginForm();
    step("Enter email: {0}");
    loginPage.enterEmail(sEmail);
    step("Enter password: {0}");
    loginPage.enterPassword(sPass);
    step("Click Login button");
    loginPage.getLoginButton().click();
    CabinetPage cabinet = new CabinetPage(driver);
    step("Click My menu");
    cabinet.clickMyMenu();
    assertTrue(cabinet.getMessageNameSurname().contains(sName), "Name, Surname not matching" +
            "");
    step("Click Logout");
    cabinet.clickLogOut();

  }


   @AfterClass(alwaysRun = true)
  public void tearDown()  {
     driver.quit();
     driver = null;
  }


}
