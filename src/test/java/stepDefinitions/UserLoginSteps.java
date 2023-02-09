package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

import static utils.webDriver.Builder.getWebDriver;

public class UserLoginSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage;

    @Given("I have a valid user credentials")
    public void given_i_have_a_valid_user() {
    }

    @When("I login")
    public void logIntoDelius() {
        homePage = loginPage.login();
    }

    @Then("the Homepage should appear")
    public void homepageShouldAppear() {
        /** Safari requires the following wait or it gets the login page title
         *  - we'll need to replace this with tolerantInteractions as per the Evoco framework */
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("National Search")));
    }

}
