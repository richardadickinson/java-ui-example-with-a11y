package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.EVENT;
import static stepDefinitions.BaseSteps.getEventSessionData;
import static testDataApi.TestDataApiUtils.*;

public class Event {
    static Endpoints event = EVENT;

    /**
     * Base methods to interact with the API - they should contain no extra logic or setup
     */
    public static Map<String, Object> getEvent(String eventId) {
        Response response = get(event.getEndpointName() + eventId);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertEvent(String path, String crn) throws IOException {
        String jsonBody = generateStringFromResource(path);
        Response response = post(jsonBody, event.getEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateEventByEventID(String path, String eventId) throws IOException {
        String jsonBody = generateStringFromResource(path);
        Response response = post(jsonBody, event.getUpdateEndpointName() + eventId);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateEventByCRN(String path, String crn, String eventNumber) throws IOException {
        String jsonBody = generateStringFromResource(path);
        Response response = post(jsonBody, event.getUpdateEndpointName() + crn + "/" + eventNumber);
        return response.body().as(new TypeRef<>() {});
    }

    /**
     * Methods to use from Test Scenarios - they can set up Session Data etc.
     */
    public static String createEventReturnEventId(String path, String crn) throws IOException {
        Map<String, Object> responseBody  = insertEvent(path, crn);
        String eventId = responseBody.get("eventId").toString();
        getEventSessionData().setEventId(eventId);
        getEventSessionData().setApiResponseBody(responseBody);
        return eventId;
    }
}
