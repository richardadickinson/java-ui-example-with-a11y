package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.eventList.EventDetailsPage;

import static config.TestDataApiConfig.apiRequestPath;
import static data.SessionDataMapper.createEvent;
import static data.SessionDataMapper.createPerson;
import static stepDefinitions.BaseSteps.getSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class EventSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    EventDetailsPage eventDetailsPage;

    @Given("offender with event is created")
    public void create_offender_with_event() {
        createPerson(apiRequestPath + "create-offender.json");
        createEvent(apiRequestPath + "create-event.json");
    }

    @When("I navigate to Event Details page")
    public void navigate_to_event_details_page() {
        eventDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndSearch(getSessionData().getPerson().getCrn())
                .clickOnViewLink_singleResult()
                .clickOnEventListLink()
                .clickOnViewLink();
    }

    @Then("the event details should be present")
    public void validate_event_details() {
        eventDetailsPage.assertEventDetails();
    }

}
