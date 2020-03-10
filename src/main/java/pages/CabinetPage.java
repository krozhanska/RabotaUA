package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CabinetPage {

    private WebDriver  driver;
    @FindBy
            (css = "label > .fi-profile")
    //(css = ".fd-beefy-serf .fi-profile")
    private WebElement myMenu;


    @FindBy(css = ".f-header-name-and-avatar-wrap > .f-header-menu-list-link-with-border > .f-header-username-text" )
    private WebElement myMenuLabel;

    @FindBy(css = ".f-sidebar-menu-link:nth-child(1) > a")
    private WebElement cv;

    @FindBy (css = ".fd-fat-merchant")
    private WebElement messageNumberCV;

    @FindBy(css = "a[id*='Sidebar'][id$='Exit']")
    private WebElement logOut;


    @FindBy(css = ".f-sidebar  .f-header-username-text")
    private WebElement messageNameSurname;

    public void toMyMenu() {
        WebDriverWait wait = new WebDriverWait(this.driver, 35);
        wait.until(ExpectedConditions.elementToBeClickable(myMenu));
        myMenu.click();
    }
   // public WebElement getMyMenuLabel() { return myMenuLabel; }
    public void getCv() {
        WebDriverWait wait = new WebDriverWait(this.driver, 35);
        wait.until(ExpectedConditions.elementToBeClickable(cv));
        cv.click();
    }
    public String     getMessageNumberCV() { return messageNumberCV.getText(); }
    public String     getMessageNameSurname() { return messageNameSurname.getText(); }
    public MainPage   clickLogOut() {
        WebDriverWait wait = new WebDriverWait(this.driver, 35);
        wait.until(ExpectedConditions.elementToBeClickable(logOut));
        logOut.click();
        return new MainPage(this.driver);}

    public CabinetPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);

    }
    private CabinetPage enterText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
        return this;

    }

}
