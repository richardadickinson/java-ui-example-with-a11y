package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.AxeA11y;

public class A11yTestSteps {

    static LoginPage loginPage = new LoginPage();
    static SoftAssert sa = new SoftAssert();

    @Given("the Login page is loaded")
    public static void theLoginPageIsLoaded(){
    }

    @Then("Axe analyses the {string} page and produces a report")
    public static void axeAnalysesThePageAndProducesReport(String pageName) {
        int violationsCount = AxeA11y.checkAccessibilityViolations(pageName);
        sa.assertEquals(violationsCount, 0, violationsCount + " violations found on " + pageName + " page");
    }

    @When("the user logs in")
    public void theUserLogsIn(){
        loginPage.login();
    }

    @After("@a11y")
    public void assertSoftAsserts(){
        sa.assertAll();
    }

}
