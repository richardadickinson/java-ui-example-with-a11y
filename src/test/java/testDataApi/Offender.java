package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static stepDefinitions.BaseSteps.getOffenderSessionData;
import static testDataApi.TestDataApiUtils.*;

public class Offender {
    static Endpoints offender = OFFENDER;

    /**
     * Base methods to interact with the API - they should contain no extra logic or setup
     */
    public static Map<String, Object> getOffender(String crn) {
        Response response = get(offender.getEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertOffender(String path) throws IOException {
        String jsonBody = generateStringFromResource(path);
        Response response = post(jsonBody, offender.getEndpointName());
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateOffender(String path, String crn) throws IOException {
        String jsonBody = generateStringFromResource(path);
        Response response = post(jsonBody, offender.getUpdateEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    /**
     * Methods to use from Test Scenarios - they set up Session Data
     */
    public static String createOffender(String path) throws IOException {
        Map<String, Object> responseBody = insertOffender(path);
        getOffenderSessionData().setCrn((String) responseBody.get("crn"));
        getOffenderSessionData().setApiResponseBody(responseBody);
        return (String) responseBody.get("crn");
    }

}
