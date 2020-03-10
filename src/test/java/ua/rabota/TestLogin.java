package ua.rabota;


import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.CabinetPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    cabinet.toMyMenu();
    assertTrue(cabinet.getMessageNameSurname().contains(sName));

    cabinet.clickLogOut();

  }


  @AfterMethod(alwaysRun = true)
  public void takeScreenshot(ITestResult result) {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    String methodName = result.getName();
    if (!result.isSuccess()){
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
        String path = "../RabotaTestLogin/target/screenshots/"+methodName +"_"+format.format(calendar.getTime())+".png";
        File fileOutput  = new File (path);
        org.apache.commons.io.FileUtils.copyFile(scrFile, fileOutput);
        Reporter.log("screenshot saved at "+fileOutput.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg");
        Reporter.log("<p><img src=\""+fileOutput.getAbsolutePath()+"\" alt=\""+path+"\"></p>");
                //"<a href='../"+result.getName()+".jpg' <img src='../"+result.getName()+".jpg' hight='100' width='100'/> </a>");
        //Reporter.log("<a href='.."+ path +"'"+"><img scr='.."+ path+" height = '100' widh = '100' />screenshot</a>");
      } catch (IOException e){
        e.printStackTrace();
      }
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    SingletonWB.killWD();
  }


}
