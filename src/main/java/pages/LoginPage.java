package pages;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver  driver;
    @FindBy(id = "ctl00_Sidebar_login_txbLogin")
    //Sidebar_login_txbLogin
    private WebElement useremail;

    @FindBy(id = "ctl00_Sidebar_login_txbPassword")
    //Sidebar_login_txbPassword
    private WebElement password;

    @FindBy(id = "ctl00_Sidebar_login_lnkLogin")
    //Sidebar_login_lnkLogin
    private WebElement loginButton;

    @FindBy(id = "ctl00_content_ZoneLogin_pLogin")
    //@FindBy(xpath = "//span[@class='error-message']")
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
    @Contract("_, _ -> this")
    public void enterText(@NotNull WebElement element, String text){
        element.clear();
        element.sendKeys(text);

    }

    public void enterPassword(String pass){
        enterText(password, pass);
    }

    public void enterEmail(String email){
        enterText(useremail, email);
    }



}
