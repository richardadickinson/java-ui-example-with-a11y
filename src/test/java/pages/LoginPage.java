package pages;

import config.Users;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.PageNavigation.LOGIN_PAGE;

public class LoginPage extends BasePageObject {
    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(css = ".btn-success")
    private WebElement loginButton;

    public LoginPage() {
        super(LOGIN_PAGE);
    }

    public HomePage login() {
        usernameField.sendKeys(Users.UMT_ADMIN.getUsername());
        passwordField.sendKeys(Users.UMT_ADMIN.getPassword());
        loginButton.click();
        return new HomePage();
    }

}
