package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static stepDefinitions.BaseSteps.getOffenderSessionData;
import static testDataApi.TestDataApiUtils.*;

public class Offender {
    static Endpoints offender = OFFENDER;

    public static Response getOffender(String crn) {
        return get(offender.getEndpointName() + crn);
    }

    public static Response createOffender(String path) throws IOException {
        String jsonBody = generateStringFromResource(path);
        //System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, offender.getEndpointName());
    }

    public static String createOffenderAndGetCrn(String path) throws IOException {
        Response response = createOffender(path);
        Map<String, Object> responseBody = response.body().as(new TypeRef<>() {
        });
        String crn = (String) responseBody.get("crn");
        getOffenderSessionData().setCrn(crn);
        getOffenderSessionData().setApiResponseBody(responseBody);
        return crn;
    }

    public static Response updateOffender(String path, String crn) throws IOException {
        String jsonBody = generateStringFromResource(path);
        return post(jsonBody, offender.getUpdateEndpointName() + crn);
    }

}
