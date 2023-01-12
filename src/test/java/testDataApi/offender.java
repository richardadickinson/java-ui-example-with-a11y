package testDataApi;

import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.TestDataApiConfig.Endpoints;
import static config.TestDataApiConfig.Endpoints.OFFENDER;
import static testDataApi.testDataApiUtils.Get;
import static testDataApi.testDataApiUtils.Post;

public class offender
{
    static Endpoints offender = OFFENDER;

    private static String generateStringFromResource(String path) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static Response GetOffender(String crn)
    {
        return Get(offender.getEndpointName() + crn);
    }
    public static Response CreateOffender(String path) throws IOException
    {
        String jsonBody = generateStringFromResource(path);
        //System.out.println(jsonBody);  //DEBUG
        return Post(jsonBody, offender.getEndpointName());
    }

    public static Response UpdateOffender(String path, String crn) throws IOException
    {
        String jsonBody = generateStringFromResource(path);
        String endpointBuilder = offender.getUpdateEndpointName() + crn;
        return Post(jsonBody, endpointBuilder);
    }




}
