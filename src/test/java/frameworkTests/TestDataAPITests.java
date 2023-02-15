package frameworkTests;

import config.TestDataApiConfig;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import testDataApi.Contact;
import testDataApi.Event;
import testDataApi.Offender;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static config.TestDataApiConfig.apiRequestPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesPattern;
import static org.testng.Assert.assertThrows;
import static testDataApi.TestDataApiUtils.*;

public class TestDataAPITests {

    static TestDataApiConfig.Endpoints offender = OFFENDER;

    @Test
    public void testGetThrowsAssertionErrorWithInvalidParam(){
        assertThrows(AssertionError.class, ()-> get(offender.getEndpointName() + "XXXX"));
    }
    @Test
    public void testThrowsAssertionErrorWithInvalidJson(){
        assertThrows(AssertionError.class, ()-> post(apiRequestPath + "update-event.json", offender.getEndpointName()));
    }

    @Test
    public void testCanInsertNewOffender() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        Assert.assertEquals(body.get("firstName"), "TestDataAPIOffendertest");
        Assert.assertNotNull(body.get("crn"));
        assertThat((String) body.get("crn"), matchesPattern("^X\\w{6}"));
    }

    @Test
    public void testCanGetOffenderByCRN() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");

        Map<String, Object> respBody = Offender.getOffender(testCrn);
        Assert.assertNotNull(respBody.get("crn"));
        Assert.assertEquals(testCrn, (String) respBody.get("crn"));
        Assert.assertEquals(respBody.get("thirdName"), "New");
    }

    @Test
    public void testCanUpdateOffender() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");

        Map<String, Object> respBody = Offender.updateOffender(apiRequestPath + "update-offender.json", testCrn);
        Assert.assertEquals(respBody.get("preferredName"), "Criminal");
        Assert.assertEquals(respBody.get("thirdName"), "Cheese");
    }

    @Test
    public void testCanInsertNewEvent() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");

        Map<String, Object> respBody = Event.insertEvent(apiRequestPath + "create-event.json", testCrn);
        Assert.assertNotNull(respBody.get("eventId"));
        assertThat(respBody.get("eventId").toString(), matchesPattern("\\d{10}"));
        Assert.assertEquals(respBody.get("mainOffenceCode"), "00856");
    }

    @Test
    public void testCanGetEventById() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");

        Map<String, Object> respBody = Event.insertEvent(apiRequestPath + "create-event.json", testCrn);
        String eventId = respBody.get("eventId").toString();
        Map<String, Object> getBody = Event.getEvent(eventId);
        Assert.assertEquals(getBody.get("eventId").toString(), eventId);
    }

    @Test
    public void testCanUpdateEventById() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");
        Map<String, Object> respBody = Event.insertEvent(apiRequestPath + "create-event.json", testCrn);
        String eventId = respBody.get("eventId").toString();

        Map<String, Object> updateBody = Event.updateEventByEventID(apiRequestPath + "update-event.json", eventId);
        Assert.assertEquals(updateBody.get("eventId").toString(), eventId);
        Assert.assertEquals(updateBody.get("convictionDate"), "2017-12-02T00:00:00Z[UTC]");
    }

    @Test
    public void testCanUpdateEventByCRN() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");
        Map<String, Object> respBody = Event.insertEvent(apiRequestPath + "create-event.json", testCrn);
        String eventId = respBody.get("eventId").toString();

        Map<String, Object> updateBody = Event.updateEventByCRN(apiRequestPath + "update-event.json", testCrn, "1");
        Assert.assertEquals(updateBody.get("eventId").toString(), eventId);
        Assert.assertEquals(updateBody.get("convictionDate"), "2017-12-02T00:00:00Z[UTC]");
    }

    @Test
    public void testCanCreateContact() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");

        Map<String, Object> contactBody = Contact.insertContact(apiRequestPath + "create-contact.json", testCrn);
        Assert.assertNotNull(contactBody.get("contactID"));
        assertThat(contactBody.get("contactID").toString(), matchesPattern("\\d{10}"));
        Assert.assertEquals(contactBody.get("offenderCRN"), testCrn);
        Assert.assertEquals(contactBody.get("contactType"), "C378");
    }

    @Test
    public void testCanUpdateContact() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");
        Map<String, Object> contactBody = Contact.insertContact(apiRequestPath + "create-contact.json", testCrn);
        String contactId = contactBody.get("contactID").toString();

        Contact.updateContact(apiRequestPath + "update-contact.json", contactId, testCrn);

        Map<String, Object> getBody = Contact.getContact(contactId);
        Assert.assertEquals(getBody.get("contactID").toString(), contactId);
        Assert.assertEquals(getBody.get("offenderCRN"), testCrn);
        Assert.assertTrue(getBody.get("notes").toString().contains("testing notes for updating contact by Id"));
    }

    @Test
    public void testCanGetContactById() throws IOException {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        String testCrn = (String) body.get("crn");
        Map<String, Object> contactBody = Contact.insertContact(apiRequestPath + "create-contact.json", testCrn);
        String contactId = contactBody.get("contactID").toString();

        Map<String, Object> getBody = Contact.getContact(contactId);
        Assert.assertEquals(getBody.get("contactID").toString(), contactId);
        Assert.assertEquals(getBody.get("offenderCRN"), testCrn);
    }

    @Test
    public void testCanUpdateValueInJsonFile(){
        String jsonBody = updateValueInJson(apiRequestPath + "create-contact.json", "offenderCRN", "testValue");
        assertThat(jsonBody, containsString("\"offenderCRN\":\"testValue\""));
    }
    @Test
    public void testUpdateValueInJsonReturnsErrorTextWhenJsonFileNotFound(){
        String output = updateValueInJson(apiRequestPath + "rubbish", "offenderCRN", "testValue");
        Assert.assertEquals(output, "Error in json update");
    }
    @Test
    public void testUpdateValueInJsonReturnsErrorTextWhenJsonParameterNotFound(){
        String output = updateValueInJson(apiRequestPath + "create-contact.json", "rubbish", "testValue");
        Assert.assertEquals(output, "Error in json update");
    }

    @Test
    public void testJsonFileIsConvertedToString() throws IOException {
        String output = generateStringFromResource("src/test/resources/fixtures/json-to-string-test.json");
        Assert.assertEquals(output, """
                {
                  "value": "some value",
                  "testValue": {
                    "sample": "sample text"
                  }
                }""");
    }
    @Test
    public void testGenerateStringFromResourceThrowsErrorWhenFileDoesNotExist(){
        assertThrows(NoSuchFileException.class, ()-> generateStringFromResource("rubbish"));
    }

    @AfterClass
    public void cleanUp(){
        // TODO: delete all the test data we've created here
    }

}
