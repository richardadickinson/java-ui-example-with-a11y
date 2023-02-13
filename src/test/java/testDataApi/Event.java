package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.EVENT;
import static stepDefinitions.BaseSteps.*;
import static testDataApi.TestDataApiUtils.*;

public class Event {
    static Endpoints event = EVENT;

    public static Response getEvent(String eventId) {
        return get(event.getEndpointName() + eventId);
    }

    public static Response createEvent(String path, String crn) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, event.getEndpointName() + crn);
    }

    public static String createEventAndGetEventId(String path, String crn) throws IOException {
        Response response = createEvent(path, crn);
        Assert.assertEquals(response.statusCode(), 201);
        Map<String, Object> responseBody = response.body().as(new TypeRef<>() {});
        String eventId = responseBody.get("eventId").toString();
        getEventSessionData().setEventId(eventId);
        getEventSessionData().setApiResponseBody(responseBody);
        return eventId;
    }

    public static Response updateEventByEventID(String path, String eventId) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, event.getUpdateEndpointName() + eventId);
    }

    public static Response updateEventByCRN(String path, String crn, String eventNumber) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, event.getUpdateEndpointName() + crn + "/" + eventNumber);
    }

}
