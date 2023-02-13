package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.contactList.ContactDetailsPage;

import static stepDefinitions.BaseSteps.getOffenderSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class ContactSteps {
    LoginPage loginPage = new LoginPage(getWebDriver());
    ContactDetailsPage contactDetailsPage;

    @When("I navigate to Contact Details page")
    public void navigate_to_contact_details_page() {
        contactDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(getOffenderSessionData().getCrn())
                .clickOnViewLink()
                .clickOnContactListLink()
                .clickOnViewLink();
    }

    @Then("the contact details should be present")
    public void validate_contact_details() {
        contactDetailsPage.assertContactDetails();
    }

}
