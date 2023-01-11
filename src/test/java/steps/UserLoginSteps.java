package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class UserLoginSteps {

    LoginPage loginPage;
    HomePage homePage;


    @Given("I have a valid user credentials")
    public void given_i_have_a_valid_user(){
        System.out.println("test 123");

    }

    @When("I login")
    public void logIntoDelius() {
        loginPage = new LoginPage();
        loginPage.login();
    }

    @Then("the Homepage should appear")
    public void homepageShouldAppear() {
        homePage = new HomePage();
        homePage.assertPageTitle();

    }

}
