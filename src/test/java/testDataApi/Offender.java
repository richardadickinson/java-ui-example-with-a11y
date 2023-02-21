package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static testDataApi.TestDataApiUtils.*;

public class Offender {
    private static final Logger logger = LoggerFactory.getLogger(Offender.class);

    private static final Endpoints offender = OFFENDER;

    public static Map<String, Object> getOffender(String crn) {
        Response response = get(offender.getEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> insertOffender(String path) {
        String jsonBody = generateStringFromResource(path);
        if (null == jsonBody) return null;

        Response response;
        try {
            response = post(jsonBody, offender.getEndpointName());
        } catch (Exception e) {
            logger.debug(e.getMessage());
            logger.debug("Test Data API POST failed...retrying...");
            response = post(jsonBody, offender.getEndpointName());
        }

        return response.body().as(new TypeRef<>() {});
    }

    public static Map<String, Object> updateOffender(String path, String crn) {
        String jsonBody = generateStringFromResource(path);
        if (null == jsonBody) return null;

        Response response = post(jsonBody, offender.getUpdateEndpointName() + crn);
        return response.body().as(new TypeRef<>() {});
    }

}
