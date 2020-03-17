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

public class TestLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Parameters({"browserValue"})
  @BeforeClass(alwaysRun = true)
  public void setUp(String browserValue) throws Exception {
    driver = SingletonWB.getInstance (browserValue);
    baseUrl = "https://rabota.ua";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test(groups = { "smoketest" })
  public void testLog() throws Exception {
    driver.get(baseUrl);
    MainPage main = new MainPage(driver);
    main.setEnterLogin();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterEmail("emailns@gmail.com");
    loginPage.enterPassword("123");
    loginPage.getLoginButton().click();

    assertTrue(loginPage.getNotValidCredanceMessage().getText().
            contains("Неправильный логин или пароль."));

  }


  @Test(dataProvider = "Authentication", dataProviderClass = DataProviderTest.class,
          groups = { "functest"})
  public void testLoginPositive(String sEmail, String sPass, String sName, String NumberCV){
    driver.get(baseUrl);
    MainPage main = new MainPage(driver);
    LoginPage loginPage = main.openLoginForm();
    loginPage.enterEmail(sEmail);
    loginPage.enterPassword(sPass);
    loginPage.getLoginButton().click();
    CabinetPage cabinet = new CabinetPage(driver);
    cabinet.clickMyMenu();
    assertTrue(cabinet.getMessageNameSurname().contains(sName));

    cabinet.clickLogOut();

  }


   @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    SingletonWB.killWD();
  }


}
