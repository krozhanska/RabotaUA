package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BrowserSetup {

    private WebDriver driver;
    @FindBy(css = ".f-header-menu-list-link-with-border > label")
    private WebElement enterLogin;
    @FindBy(css = "a.f-header-logo.fd-f-center-middle")
    private WebElement mainLogo;
    @Step("Open Login form")
    public void openLoginForm() {
        enterLogin.click();
        //return new LoginPage(this.driver);
    }

    public WebElement getMainLogo() {
        return mainLogo;
    }

    //public By getBy (){return By.cssSelector("a.f-header-menu-list-link-with-border > label > span");}

    public MainPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);

    }

    public void setEnterLogin() {
        enterLogin.click();
    }

    /*public WebDriver getDriver() {
        return driver;
    }*/

}
