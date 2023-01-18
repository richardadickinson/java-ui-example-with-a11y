package testDataApi;

import io.restassured.response.Response;

import java.io.IOException;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.EVENT;
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

    public static Response updateEventByEventID(String path, String eventId) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, event.getUpdateEndpointName() + eventId);
    }

    public static Response updateEventByCRN(String path, String crn, String eventNumber) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, event.getUpdateEndpointName() + crn + "/" + eventNumber);
    }

}
