package testDataApi;

import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static testDataApi.TestDataApiUtils.get;
import static testDataApi.TestDataApiUtils.post;

public class Offender
{
    static Endpoints offender = OFFENDER;

    private static String generateStringFromResource(String path) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
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

    public static Response updateOffender(String path, String crn) throws IOException
    {
        String jsonBody = generateStringFromResource(path);
        String endpointBuilder = offender.getUpdateEndpointName() + crn;
        return post(jsonBody, endpointBuilder);
    }




}
