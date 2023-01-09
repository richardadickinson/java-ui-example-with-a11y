package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import utils.WebdriverUtils;

public class LoginPage extends PageObject {

    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(css = ".btn-success [text=Login]")
    private WebElement loginButton;

    private static final String baseUrl = "https://ndelius.test.probation.service.justice.gov.uk";
    private static final String pageUrl = "/NDelius-war/delius/JSP/auth/login.jsp";
    WebdriverUtils webdriverUtils;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void login(){
        webdriverUtils.launchBrowser( baseUrl+pageUrl);
        usernameField.sendKeys("NDelius21");
        passwordField.sendKeys("Password1");
        loginButton.click();
    }


}
