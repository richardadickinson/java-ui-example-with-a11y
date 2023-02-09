package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.casemanagement.contactlist.ContactDetailsPage;

import static stepDefinitions.BaseSteps.sessionData;
import static utils.webdriver.WebDriverUtils.getWebDriver;

public class ContactSteps {
    LoginPage loginPage = new LoginPage(getWebDriver());
    ContactDetailsPage contactDetailsPage;

    @When("I navigate to Contact Details page")
    public void navigate_to_contact_details_page() {
        contactDetailsPage = loginPage
                .login()
                .clickOnNationalSearch()
                .enterCrnAndClickSearchButton(sessionData.getCrn())
                .clickOnViewLink()
                .clickOnContactListLink()
                .clickOnViewLink();
    }

    @Then("the contact details should be present")
    public void validate_something_on_contact_details_page() {
        System.out.println("do some kind of validation here comparing json to ui here");
    }

}
