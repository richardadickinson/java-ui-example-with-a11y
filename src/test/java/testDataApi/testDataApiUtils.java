package testDataApi;

import config.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static config.TestDataApiConfig.BaseAddress;
import static config.Users.API_LOGIN_USER;
import static io.restassured.RestAssured.given;

public class testDataApiUtils
{
    static Users apiLoginUser = API_LOGIN_USER;
    static int statusCode = 201;

    public static Response Get(String endpoint)
    {
        return
                given()
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(BaseAddress + endpoint)
                        .then()
                        .statusCode(200)
                        .extract().response();

    }

    public static Response Post(String jsonBody, String endPoint)
    {
        if (endPoint.contains("update")) statusCode = 200;
        return
                given()
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .when()
                        .post(BaseAddress + endPoint)
                        .then()
                        //.log().all()  //DEBUG
                        .statusCode(statusCode)
                        .extract().response();
    }
}
