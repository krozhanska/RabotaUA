package pages;

/**
 * Created by k.rozhanska on 20.06.2017.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver  driver;
    @FindBy(id = "Sidebar_login_txbLogin")
    private WebElement useremail;

    @FindBy(id = "Sidebar_login_txbPassword")
    private WebElement password;

    @FindBy(id = "Sidebar_login_lnkLogin")
    private WebElement loginButton;

    @FindBy(id = "content_ZoneLogin_pLogin")
    private WebElement notValidCredanceMessage;

    @FindBy(css = "span.error-message")
    private WebElement notValidEmailMessage;

    @FindBy(css = "a.f-header-logo.fd-f-center-middle")
    private WebElement exitButton;

    public WebElement getUseremail() {  return useremail;  }
    public WebElement getPassword() { return password;  }
    public WebElement getLoginButton() { return loginButton; }
    public WebElement getNotValidCredanceMessage() { return notValidCredanceMessage; }
    public WebElement getNotValidEmailMessage() { return notValidEmailMessage; }
    public WebElement getExitButton() { return exitButton; }

    public LoginPage (WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);

    }
    private LoginPage enterText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
        return this;

    }

    public LoginPage enterPassword(String pass){return enterText(password, pass); }

    public LoginPage enterEmail(String email){
        return enterText(useremail, email);
    }



}
