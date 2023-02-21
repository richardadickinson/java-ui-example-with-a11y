package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.caseManagement.personalDetails.PersonalDetailsPage;

import java.time.Duration;

import static utils.webDriver.Builder.getWebDriver;

public class UserLoginSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    HomePage homePage;
    PersonalDetailsPage personalDetailsPage;

    @Given("I have a valid user credentials")
    public void given_i_have_a_valid_user() {
    }

    @When("I login")
    public void logIntoDelius() {
        homePage = loginPage.login();
    }

    @Then("the Homepage should appear")
    public void homepageShouldAppear() {
        /**
         * Safari requires the following wait or it gets the login page title
         *  - we'll need to replace this with tolerantInteractions
         *  */
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("National Search")));
    }

    @Given("I search for and select an offender")
    public void iSearchForAndSelectAnOffender() {
        personalDetailsPage = loginPage.login()
                .clickOnNationalSearch()
                .enterPersonNameAndSearch("tom","jones")
                .selectSearchResultsViewLinkByCRN("X289671")
                .clickOnPersonalDetailsLink();
    }

    @Then("The Personal Details page matches the offender selected")
    public void thePersonalDetailsPageMatches() {
        Assert.assertEquals(personalDetailsPage.crnField.getText(), "X289671");
        Assert.assertEquals(personalDetailsPage.firstNameField.getText(), "TomMehWW");
        Assert.assertEquals(personalDetailsPage.surnameField.getText(), "JonKoiYY");
    }
}
