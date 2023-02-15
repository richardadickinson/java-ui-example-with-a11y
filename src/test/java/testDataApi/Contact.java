package testDataApi;

import config.TestDataApiConfig.Endpoints;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.Map;

import static testDataApi.TestDataApiUtils.*;

public class Contact {
    private static Endpoints contact = Endpoints.CONTACT;

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

}
