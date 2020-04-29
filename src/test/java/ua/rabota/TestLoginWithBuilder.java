package ua.rabota;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertTrue;

@Listeners({CustomTestListener.class})
public class TestLoginWithBuilder {
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
    step("Open Login form with builder");
    main.setEnterLogin();
    LoginPageBuilder loginPage = new LoginPageBuilder.Builder(driver)
                                                     .enterEmail("emailns@gmail.com")
                                                     .enterPassword("123").build();
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
    step("Open Login form with builder");
    main.openLoginForm();
    LoginPageBuilder loginPage = new LoginPageBuilder.Builder(driver)
                                                      .enterEmail(sEmail)
                                                      .enterPassword(sPass).build();
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
