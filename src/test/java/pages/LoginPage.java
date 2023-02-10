package pages;

import config.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.webDriver.Builder.getWebDriver;

public class LoginPage extends BasePageObject {

    private static final String expectedPageTitle = "National Delius - Login";

    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(css = ".btn-success")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public HomePage login() {
        usernameField.sendKeys(Users.PERF_USER.getUsername());
        passwordField.sendKeys(Users.PERF_USER.getPassword());
        loginButton.click();
        return new HomePage(getWebDriver());
    }

}
