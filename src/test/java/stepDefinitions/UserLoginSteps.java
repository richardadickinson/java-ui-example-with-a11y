package stepDefinitions;

import data.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.caseManagement.personalDetails.PersonalDetailsPage;

import java.time.Duration;

import static config.TestDataApiConfig.apiRequestPath;
import static data.SessionDataMapper.createMultiplePersons;
import static data.SessionDataMapper.createPerson;
import static stepDefinitions.BaseSteps.getSessionData;
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
         * Safari requires the following wait, or it gets the login page title
         *  - we'll need to replace this with tolerantInteractions
         *  */
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("National Search")));
    }

    @Given("I search for and select an offender")
    public void iSearchForAndSelectAnOffender() {
        personalDetailsPage = loginPage.login()
                .clickOnNationalSearch()
                .enterPersonNameAndSearch("tom", "jones")
                .selectSearchResultsViewLinkByCRN("X289671")
                .clickOnPersonalDetailsLink();
    }

    @Then("The Personal Details page matches the offender selected")
    public void thePersonalDetailsPageMatches() {
        personalDetailsPage.simpleAssertOffender("X289671", "TomMehWW", "JonKoiYY");
    }

    @When("I search for and select second person")
    public void iSearchForAndSelectSecondPerson() {
        personalDetailsPage = loginPage.login()
                .clickOnNationalSearch()
                .enterCrnAndSearch(getSessionData().getPersons().get(1).getCrn())
                .clickOnViewLink_singleResult()
                .clickOnPersonalDetailsLink();
    }

    @Then("The Personal Details page matches the person selected")
    public void thePersonalDetailsPageMatchesThePersonSelected() {
        Person person = getSessionData().getPersons().get(1);
        personalDetailsPage.simpleAssertOffender(person.getCrn(), person.getFirstName(), person.getSurname());
    }

    @Given("I create {int} persons at once")
    public void iCreateMultiplePersonsAtOnce(int numberOfPersons) {
        createMultiplePersons(apiRequestPath + "create-offender.json", numberOfPersons); // this will create persons with the same json
    }

    @Given("I create multiple persons one at a time")
    public void iCreateMultiplePersonsOneAtATime() {
        createPerson(apiRequestPath + "create-offender.json");
        createPerson(apiRequestPath + "create-offender.json"); // this could be used to create a person with a different json
    }


}
