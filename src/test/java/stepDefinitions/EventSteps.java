package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.eventList.EventDetailsPage;
import testDataApi.Event;
import testDataApi.Offender;

import java.io.IOException;

import static config.TestDataApiConfig.apiRequestPath;
import static stepDefinitions.BaseSteps.getEventSessionData;
import static stepDefinitions.BaseSteps.getOffenderSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class EventSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    EventDetailsPage eventDetailsPage;

    @Given("offender with event is created")
    public void create_offender_with_event() throws IOException {
        String crn = Offender.createOffender(apiRequestPath + "create-offender.json");
        getOffenderSessionData().setCrn(crn);
        String eventId = Event.createEventReturnEventId(apiRequestPath + "create-event.json", crn);
        getEventSessionData().setEventId(eventId);
    }

    @When("I navigate to Event Details page")
    public void navigate_to_event_details_page() {
        eventDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(getOffenderSessionData().getCrn())
                .clickOnViewLink()
                .clickOnEventListLink()
                .clickOnViewLink();
    }

    @Then("the event details should be present")
    public void validate_event_details() {
        eventDetailsPage.assertEventDetails();
    }

}
