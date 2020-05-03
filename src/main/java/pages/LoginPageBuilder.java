package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageBuilder extends BrowserSetup {

    private WebDriver  driver;
    @FindBy(id = "ctl00_Sidebar_login_txbLogin")
    private WebElement useremail;

    @FindBy(id = "ctl00_Sidebar_login_txbPassword")
    //Sidebar_login_txbPassword
    private WebElement password;

    @FindBy(id = "ctl00_Sidebar_login_lnkLogin")
    //Sidebar_login_lnkLogin
    private WebElement loginButton;

    @FindBy(id = "ctl00_content_ZoneLogin_pLogin")
    private WebElement notValidCredanceMessage;

    @FindBy(css = "span.error-message")
    private WebElement notValidEmailMessage;

    @FindBy(css = "a.f-header-logo.fd-f-center-middle")
    private WebElement exitButton;

    public WebElement getUseremail() {  return useremail;  }
    public WebElement getPassword() { return password;  }
    @Step("Switch to the Password field")
    public void       clickPassword() { password.click();  }
    public WebElement getLoginButton() { return loginButton; }
    public WebElement getNotValidCredanceMessage() { return notValidCredanceMessage; }
    public WebElement getNotValidEmailMessage() { return notValidEmailMessage; }
    public WebElement getExitButton() { return exitButton; }
    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);

    }
    public LoginPageBuilder(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }
    public static class Builder {
        private LoginPageBuilder loginPageBuilder;


        public Builder(WebDriver webDriver) {
            loginPageBuilder = new LoginPageBuilder(webDriver);
        }

        @Step("Enter password: {0}")

        public Builder enterPassword(String pass) {
            loginPageBuilder.enterText(loginPageBuilder.getPassword(), pass);
            return this;
        }

        @Step("Enter email: {0}")
        public Builder enterEmail(String email) {
            loginPageBuilder.enterText(loginPageBuilder.getUseremail(), email);
            return this;
        }
        public LoginPageBuilder build(){
            return loginPageBuilder;
        }

    }
    public WebDriver getDriver() {
        return driver;
    }
}
