package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.casemanagement.personaldetails.PersonalDetailsPage;

import static stepDefinitions.BaseSteps.sessionData;
import static utils.webDriver.Builder.getWebDriver;

public class OffenderSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    PersonalDetailsPage personalDetailsPage;

    @When("I navigate to Personal Details page")
    public void navigate_to_event_details_page() {
        personalDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(sessionData.getCrn())
                .clickOnViewLink()
                .clickOnPersonalDetailsLink();
    }

    @Then("the offender details should be present")
    public void validate_something_on_personal_details_page() {
        System.out.println("do some kind of validation here comparing json to ui here");
    }

}
