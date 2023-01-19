package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import testDataApi.Contact;
import testDataApi.Event;
import testDataApi.Offender;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.apiRequestPath;
import static db.DatabaseAssertions.assertOffenderDetail;
import static org.testng.Assert.assertEquals;

public class TestDataApiSteps {

    private Response insertResponse;
    private Response updateResponse;
    private Response getResponse;
    private String crn;
    private String eventId;
    private String contactId;

    //Scenario: Create, update and Get an offender
    @Given("Create offender endpoint is called")
    public void create_offender_endpoint_is_called() throws IOException {
        insertResponse = Offender.createOffender(apiRequestPath + "create-offender.json");
    }

    @Then("offender is created with new CRN")
    public void offender_is_created_with_new_CRN() {
        Assert.assertEquals(insertResponse.statusCode(), 201);
        Map<String, Object> respBody = insertResponse.body().as(new TypeRef<>() {
        });
        System.out.println("CRN: " + respBody.get("crn"));
        crn = (String) respBody.get("crn");
        Assert.assertEquals(respBody.get("preferredName"), "PreferredName");
        Assert.assertNotNull(respBody.get("crn"));
    }

    @When("offender is updated")
    public void offender_is_updated() throws IOException {
        updateResponse = Offender.updateOffender(apiRequestPath + "update-offender.json", crn);
        assertEquals(updateResponse.statusCode(), 200);
    }

    @Then("offender updates are validated by GET call")
    public void offender_updates_are_validated_by_GET_call() throws Throwable {
        getResponse = Offender.getOffender(crn);
        Assert.assertEquals(getResponse.statusCode(), 200);
        Map<String, Object> respBody = getResponse.body().as(new TypeRef<>() {
        });
        Assert.assertEquals(respBody.get("preferredName"), "Criminal");
        Assert.assertEquals(respBody.get("thirdName"), "Cheese");
        assertOffenderDetail(crn, respBody.get("preferredName").toString());//Todo: add DB check here
    }

    //Scenario: Create, update and Get an Event
    @Given("an offender with event is created")
    public void an_offender_with_event_is_created() throws IOException {
        crn = Offender.createOffenderGetCRN(apiRequestPath + "create-offender.json");
        insertResponse = Event.createEvent(apiRequestPath + "create-event.json", crn);
        Assert.assertEquals(insertResponse.statusCode(), 201);
    }

    @Then("event is created with new event Id")
    public void event_is_created_with_new_eventId() {
        Map<String, Object> respBody = insertResponse.body().as(new TypeRef<>() {
        });
        Assert.assertEquals(respBody.get("eventNumber"), "1");
        Assert.assertNotNull(respBody.get("eventId"));
        eventId = String.valueOf(respBody.get("eventId"));
        System.out.println(eventId);
    }

    @When("event is updated by CRN")
    public void event_is_updated_by_CRN() throws IOException {
        updateResponse = Event.updateEventByCRN(apiRequestPath + "update-event.json", crn, "1");
        Assert.assertEquals(updateResponse.statusCode(), 200);
    }

    @When("event is updated by Event ID")
    public void event_is_updated_by_EventID() throws IOException {
        updateResponse = Event.updateEventByEventID(apiRequestPath + "update-event.json", eventId);
        assertEquals(updateResponse.statusCode(), 200);
    }

    @Then("event updates are validated by GET call")
    public void event_updates_are_validated_by_GET_call() {
        getResponse = Event.getEvent(eventId);
        Map<String, Object> respBody = getResponse.body().as(new TypeRef<>() {
        });
        //System.out.println(respBody.values());  //DEBUG
        Assert.assertEquals(respBody.get("convictionDate"), "2017-12-02T00:00:00Z[UTC]");
    }

    //Scenario: Create, update and GET a Contact
    @Given("an offender with contact is created")
    public void offender_with_contact_created() throws IOException {
        crn = Offender.createOffenderGetCRN(apiRequestPath + "create-offender.json");
        insertResponse = Contact.createContact(apiRequestPath + "create-contact.json", crn);
        Assert.assertEquals(insertResponse.statusCode(), 201);
    }

    @Then("contact is created with a contact Id")
    public void contact_created_with_contactId() {
        Map<String, Object> respBody = insertResponse.body().as(new TypeRef<>() {
        });
        Assert.assertEquals(respBody.get("offenderCRN"), crn);
        Assert.assertNotNull(respBody.get("contactID"));
        contactId = String.valueOf(respBody.get("contactID"));
        System.out.println(contactId);
    }

    @When("contact is updated")
    public void contact_updated() {
        updateResponse = Contact.updateContact(apiRequestPath + "update-contact.json", contactId, crn);
        Assert.assertEquals(updateResponse.statusCode(), 200);
    }

    @Then("contact updates are validated by GET call")
    public void contact_updates_validated_by_get() {
        getResponse = Contact.getContact(contactId);
        Assert.assertEquals(getResponse.statusCode(), 200);
        Map<String, Object> respBody = getResponse.body().as(new TypeRef<>() {
        });
        //System.out.println(respBody.values());  //DEBUG
        Assert.assertTrue(respBody.get("notes").toString().contains("testing notes for updating contact by Id"));
    }

}
