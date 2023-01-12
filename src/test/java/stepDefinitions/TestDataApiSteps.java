package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import testDataApi.Offender;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.apiTestRequestPath;

public class TestDataApiSteps {

    private Response insertResponse;
    private Response updateResponse;
    private Response getResponse;
    private String crn;
    @Given("Create offender endpoint is called")
    public void create_offender_endpoint_is_called() throws IOException
    {
        insertResponse = Offender.createOffender(apiTestRequestPath + "create-offender.json");
    }
    @Then("offender is created with new CRN")
    public void offender_is_created_with_new_CRN()
    {
        Assert.assertEquals(insertResponse.statusCode(), 201);
        Map<String,Object> respBody = insertResponse.body().as(new TypeRef<>() {});
        System.out.println("CRN: " + respBody.get("crn"));
        //String threadId = "Thread ID" + Thread.currentThread().getId();
        //System.out.println(threadId);
        crn = (String) respBody.get("crn");
        Assert.assertEquals(respBody.get("preferredName"), "PreferredName");
        Assert.assertNotNull(respBody.get("crn"));
    }

    @When("offender is updated")
    public void offender_is_updated() throws IOException {
        updateResponse = Offender.updateOffender(apiTestRequestPath + "update-offender.json", crn);
        Assert.assertEquals(updateResponse.statusCode(), 200);
    }
    @Then("updates are validated by GET call")
    public void updates_are_validated_by_GET_call()
    {
        getResponse = Offender.getOffender(crn);
        Assert.assertEquals(getResponse.statusCode(), 200);
        Map<String,Object> respBody = getResponse.body().as(new TypeRef<>() {});
        Assert.assertEquals(respBody.get("preferredName"), "Criminal");
        Assert.assertEquals(respBody.get("thirdName"), "Cheese");
    }

}
