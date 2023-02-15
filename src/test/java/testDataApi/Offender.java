package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static testDataApi.TestDataApiUtils.*;

public class Offender {

    static Endpoints offender = OFFENDER;

    public static Map<String, Object> getOffender(String crn) {
        Response response = get(offender.getEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertOffender(String path) {
        String jsonBody = generateStringFromResource(path);
        if (null != jsonBody) {
            Response response = post(jsonBody, offender.getEndpointName());
            return response.body().as(new TypeRef<>() {
            });
        } else {
            return null;
        }
    }

    public static Map<String, Object> updateOffender(String path, String crn) {
        String jsonBody = generateStringFromResource(path);
        if (null != jsonBody) {
            Response response = post(jsonBody, offender.getUpdateEndpointName() + crn);
            return response.body().as(new TypeRef<>() {
            });
        } else {
            return null;
        }
    }

}
