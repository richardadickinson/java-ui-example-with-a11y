package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.eventList.EventDetailsPage;

import static stepDefinitions.BaseSteps.sessionData;
import static utils.webDriver.Builder.getWebDriver;

public class EventSteps {

    LoginPage loginPage = new LoginPage(getWebDriver());
    EventDetailsPage eventDetailsPage;

    @When("I navigate to Event Details page")
    public void navigate_to_event_details_page() {
        eventDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(sessionData.getCrn())
                .clickOnViewLink()
                .clickOnEventListLink()
                .clickOnViewLink();
    }

    @Then("the event details should be present")
    public void validate_something_on_event_details_page() {
        System.out.println("do some kind of validation here comparing json to ui here");
    }

}
