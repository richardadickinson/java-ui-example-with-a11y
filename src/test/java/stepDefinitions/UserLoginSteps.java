package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class UserLoginSteps {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("I have a valid user credentials")
    public void given_i_have_a_valid_user(){}

    @When("I login")
    public void logIntoDelius()
    {
        loginPage.login();
    }

    @Then("the Homepage should appear")
    public void homepageShouldAppear()
    {
        homePage.assertPageTitle(HomePage.pageTitle);
    }

}
