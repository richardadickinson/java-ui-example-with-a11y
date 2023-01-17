package testDataApi;

import config.TestDataApiConfig.Endpoints;
import io.restassured.response.Response;

import static testDataApi.TestDataApiUtils.*;

public class Contact
{
    static Endpoints contact = Endpoints.CONTACT;

    public static Response getContact(String contactId) {
        return get(contact.getEndpointName() + contactId);
    }

    public static Response createContact(String path, String crn)
    {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        //System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, contact.getEndpointName());
    }

    public static Response updateContact(String path, String contactId, String crn)
    {
        String jsonBody = updateValueInJson(path, "offenderCRN", crn);
        System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, contact.getUpdateEndpointName() + contactId);
    }
}
