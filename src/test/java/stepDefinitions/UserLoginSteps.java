package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class UserLoginSteps {

    LoginPage loginPage = new LoginPage();
    /**
     * No need to instantiate a new HomePage since this is done in the login() method
     */
    HomePage homePage;

    @Given("I have a valid user credentials")
    public void given_i_have_a_valid_user() {
    }

    @When("I login")
    public void logIntoDelius() {
        homePage = loginPage.login();
    }

    @Then("the Homepage should appear")
    public void homepageShouldAppear() {
    }

    @When("I navigate to search the CRN")
    public void navigate_to_page() {
        homePage.gotToNationalSearch()
                .searchOffenderWithCrn();
    }

    @Then("the CRN should be returned in the search results")
    public void assertSearchResult() throws InterruptedException {
        homePage.gotToNationalSearch()
                .searchOffenderWithCrn();
        Thread.sleep(10000);
    }


}
