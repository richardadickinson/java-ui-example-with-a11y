package testDataApi;

import config.TestDataApiConfig.Endpoints;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.Map;

import static stepDefinitions.BaseSteps.getContactSessionData;
import static testDataApi.TestDataApiUtils.*;

public class Contact {
    static Endpoints contact = Endpoints.CONTACT;

    /**
     * Base methods to interact with the API - they should contain no extra logic or setup
     */
    public static Map<String, Object> getContact(String contactId) {
        Response response = get(contact.getEndpointName() + contactId);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertContact(String path, String crn) {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        Response response = post(jsonBody, contact.getEndpointName());
        return response.body().as(new TypeRef<>() {});
    }

    /**
     * Posts to the UpdateContact endpoint return an empty Json body "{}"; no point returning anything
     */
    public static void updateContact(String path, String contactId, String crn) {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        post(jsonBody, contact.getUpdateEndpointName() + contactId);
    }


    /**
     * Methods to use from Test Scenarios - they set up Session Data
     */
    public static void createContact(String path, String crn) {
        Map<String, Object> responseBody = insertContact(path, crn);
        getContactSessionData().setContactId(responseBody.get("contactID").toString());
        getContactSessionData().setApiResponseBody(responseBody);
    }
}
