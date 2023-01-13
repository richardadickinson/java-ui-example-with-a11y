package testDataApi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static testDataApi.TestDataApiUtils.*;

public class Offender
{
    static Endpoints offender = OFFENDER;


    public static Response getOffender(String crn)
    {
        return get(offender.getEndpointName() + crn);
    }
    public static Response createOffender(String path) throws IOException
    {
        String jsonBody = generateStringFromResource(path);
        //System.out.println(jsonBody);  //DEBUG
        return post(jsonBody, offender.getEndpointName());
    }

    public static String createOffenderGetCRN(String path) throws IOException
    {
        Response resp = createOffender(path);
        Assert.assertEquals(resp.statusCode(), 201);
        Map<String,Object> respBody = resp.body().as(new TypeRef<>() {});
        System.out.println((String) respBody.get("crn"));  //DEBUG
        return (String) respBody.get("crn");
    }

    public static Response updateOffender(String path, String crn) throws IOException
    {
        String jsonBody = generateStringFromResource(path);
        String endpointBuilder = offender.getUpdateEndpointName() + crn;
        return post(jsonBody, endpointBuilder);
    }




}
