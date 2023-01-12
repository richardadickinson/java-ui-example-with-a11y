package testDataApi;

import config.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static config.TestDataApiConfig.baseAddress;
import static config.Users.API_LOGIN_USER;
import static io.restassured.RestAssured.given;

public class TestDataApiUtils
{
    static Users apiLoginUser = API_LOGIN_USER;
    static int statusCode = 201;

    public static Response get(String endpoint)
    {
        return
                given()
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseAddress + endpoint)
                        .then()
                        .statusCode(200)
                        .extract().response();

    }

    public static Response post(String jsonBody, String endPoint)
    {
        if (endPoint.contains("update")) statusCode = 200;
        return
                given()
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .when()
                        .post(baseAddress + endPoint)
                        .then()
                        //.log().all()  //DEBUG
                        .statusCode(statusCode)
                        .extract().response();
    }
}
