package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.caseManagement.contactList.ContactDetailsPage;

import static config.TestDataApiConfig.apiRequestPath;
import static data.SessionDataMapper.createContact;
import static data.SessionDataMapper.createOffender;
import static stepDefinitions.BaseSteps.getSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class ContactSteps {
    LoginPage loginPage = new LoginPage(getWebDriver());
    ContactDetailsPage contactDetailsPage;

    @Given("offender with contact is created")
    public void create_offender_with_contact() {
        createOffender(apiRequestPath + "create-offender.json");
        createContact(apiRequestPath + "create-contact.json");
    }

    @When("I navigate to Contact Details page")
    public void navigate_to_contact_details_page() {
        contactDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(getSessionData().getCrn())
                .clickOnViewLink()
                .clickOnContactListLink()
                .clickOnViewLink();
    }

    @Then("the contact details should be present")
    public void validate_contact_details() {
        contactDetailsPage.assertContactDetails();
    }

}
