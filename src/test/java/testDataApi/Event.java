package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.EVENT;
import static testDataApi.TestDataApiUtils.*;

public class Event {
    static Endpoints event = EVENT;

    public static Map<String, Object> getEvent(String eventId) {
        Response response = get(event.getEndpointName() + eventId);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertEvent(String path, String crn) {
        String jsonBody = generateStringFromResource(path);
        if (null == jsonBody) return null;

        Response response = post(jsonBody, event.getEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateEventByEventID(String path, String eventId) {
        String jsonBody = generateStringFromResource(path);
        if (null == jsonBody) return null;

        Response response = post(jsonBody, event.getUpdateEndpointName() + eventId);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateEventByCRN(String path, String crn, String eventNumber) {
        String jsonBody = generateStringFromResource(path);
        if (null == jsonBody) return null;

        Response response = post(jsonBody, event.getUpdateEndpointName() + crn + "/" + eventNumber);
        return response.body().as(new TypeRef<>() {});
    }

}
