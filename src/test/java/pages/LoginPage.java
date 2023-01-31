package pages;

import config.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {

    public static final String pageTitle = "National Delius - Login";
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(css = ".btn-success")
    private WebElement loginButton;

    public void login() {
        assertPageTitle(pageTitle);
        usernameField.sendKeys(Users.UMT_ADMIN.getUsername());
        passwordField.sendKeys(Users.UMT_ADMIN.getPassword());
        loginButton.click();
    }

}
