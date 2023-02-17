package stepDefinitions;

import data.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.personalDetails.PersonalDetailsPage;

import static config.TestDataApiConfig.apiRequestPath;
import static data.SessionDataMapper.createPerson;
import static stepDefinitions.BaseSteps.getSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class OffenderSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    PersonalDetailsPage personalDetailsPage;

    @Given("an offender is created")
    public void create_offender() {
        createPerson(apiRequestPath + "create-offender.json");
    }

    @When("I navigate to Personal Details page")
    public void navigate_to_event_details_page() {
        personalDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(getSessionData().getPerson().getCrn())
                .clickOnViewLink()
                .clickOnPersonalDetailsLink();
    }

    @Then("the offender details should be present")
    public void validate_person_details() {
        personalDetailsPage.assertPersonDetails();
    }

}
