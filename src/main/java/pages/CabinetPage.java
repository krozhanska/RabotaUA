package pages;

/**
 * Created by k.rozhanska on 20.06.2017.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CabinetPage {

    private WebDriver  driver;
    @FindBy(xpath = "//a[@class='fd-beefy-serf']//i[@class='fi-profile']" )
    //a[@class='fd-beefy-serf']//label
    //.//*[@id='Header_header']/div/header/div/div/ul/li[7]/div/label/span[2]/span
    private WebElement myMenu;


    @FindBy(xpath = "//a[@class='f-header-menu-list-link-with-border -forloggedin']//span[@class='f-header-username-text']" )
    //.//*[@id='Header_header']/div/header/div/div/ul/li[7]/div/label/span[2]/span
    private WebElement myMenuLabel;

    @FindBy(xpath = "//li[@class='f-sidebar-menu-link f-sidebar-menu-link-with-icon']//li[1]//a[1]")
    //.//*[@id='form']/aside/div/ul/li[2]/ul/li[1]/a
    //li[@class='f-sidebar-menu-link f-sidebar-menu-link-with-icon']//li[1]//a[1]
    private WebElement cv;

    @FindBy(xpath = "//span[@class='fd-fat-merchant']")
            //(css = "span.fd-fat-merchant")
    private WebElement messageNumberCV;

    @FindBy(id = "ctl00_Sidebar_loggedinJobsearcher_btnExit")
    private WebElement logOut;

    @FindBy(css = "span.f-header-menu-list-link-with-border.-forloggedin > span.f-header-username-text")
    private WebElement messageNameSurname;

    public WebElement getMyMenu() { return myMenu; }
    public WebElement getMyMenuLabel() { return myMenuLabel; }
    public WebElement getCv() { return cv; }
    public WebElement getMessageNumberCV() { return messageNumberCV; }
    public WebElement getMessageNameSurname() { return messageNameSurname; }
    public WebElement getLogOut() { return logOut; }

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
