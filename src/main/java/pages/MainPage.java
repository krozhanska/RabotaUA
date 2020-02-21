package pages;

/**
 * Created by k.rozhanska on 20.06.2017.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    private WebDriver  driver;
    @FindBy(css = ".f-header-menu-list-link-with-border > label")
            //(xpath = "//i[@class='fi-profile']")
    private WebElement enterLogin;
    @FindBy(css = "a.f-header-logo.fd-f-center-middle")
    private WebElement mainLogo;

    public WebElement getEnterLogin() { return enterLogin; }
    public WebElement getMainLogo() { return  mainLogo; }

    //public By getBy (){return By.cssSelector("a.f-header-menu-list-link-with-border > label > span");}

    public MainPage (WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);

    }

    public void setEnterLogin(){
        enterLogin.click();
    }


}
