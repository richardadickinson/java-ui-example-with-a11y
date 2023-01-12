package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {

    @FindBy(id = "j_username")
    private WebElement usernameField;

    @FindBy(id = "j_password")
    private WebElement passwordField;

    @FindBy(css = ".btn-success")
    private WebElement loginButton;

    public void login() {
        usernameField.sendKeys("NDelius21");
        passwordField.sendKeys("Password1");
        loginButton.click();

    }

}