package testDataApi;

import config.TestDataApiConfig.Endpoints;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

import static stepDefinitions.BaseSteps.getContactSessionData;
import static testDataApi.TestDataApiUtils.*;

public class Contact {
    static Endpoints contact = Endpoints.CONTACT;

    public static Response getContact(String contactId) {
        return get(contact.getEndpointName() + contactId);
    }

    public static Response createContact(String path, String crn) {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        //System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, contact.getEndpointName());
    }

    public static String createContactAndGetContactId(String path, String crn) {
        Response response = createContact(path, crn);
        Assert.assertEquals(response.statusCode(), 201);
        Map<String, Object> responseBody = response.body().as(new TypeRef<>() {});
        String contactId = responseBody.get("contactID").toString();
        getContactSessionData().setContactId(contactId);
        getContactSessionData().setApiResponseBody(responseBody);
        return contactId;
    }

    public static Response updateContact(String path, String contactId, String crn) {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        //System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, contact.getUpdateEndpointName() + contactId);
    }
}
