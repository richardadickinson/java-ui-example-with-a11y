package steps;

import io.cucumber.java.en.When;
import testDataApi.offender;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.ApiTestRequestPath;

public class TestDataApiSteps {

    private Response insertResponse;
    private Response updateResponse;
    private String crn;
    @Given("Create offender endpoint is called")
    public void create_offender_endpoint_is_called() throws IOException
    {
        insertResponse = offender.CreateOffender(ApiTestRequestPath + "create-offender.json");
    }
    @Then("offender is created with new CRN")
    public void offender_is_created_with_new_CRN()
    {
        Assert.assertEquals(insertResponse.statusCode(), 201);
        Map<String,Object> respBody = insertResponse.body().as(new TypeRef<>() {});
        System.out.println("CRN: " + respBody.get("crn"));
        crn = (String) respBody.get("crn");
        Assert.assertEquals(respBody.get("preferredName"), "PreferredName");
        Assert.assertNotNull(respBody.get("crn"));
    }

    @When("offender is updated")
    public void offender_is_updated() throws IOException {
        updateResponse = offender.UpdateOffender(ApiTestRequestPath + "update-offender.json", crn);
    }
    @Then("updates are validated")
    public void updates_are_validated()
    {
        Assert.assertEquals(updateResponse.statusCode(), 200);
        Map<String,Object> respBody = updateResponse.body().as(new TypeRef<>() {});
        Assert.assertEquals(respBody.get("preferredName"), "Criminal");
        Assert.assertEquals(respBody.get("thirdName"), "Cheese");
    }

}
