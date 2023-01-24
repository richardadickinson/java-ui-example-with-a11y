package testDataApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Users;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static config.TestDataApiConfig.apiBaseAddress;
import static config.Users.API_LOGIN_USER;
import static io.restassured.RestAssured.given;

public class TestDataApiUtils {
    static Users apiLoginUser = API_LOGIN_USER;
    static int statusCode = 201;

    static RestAssuredConfig config= RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.socket.timeout",1000)
                                .setParam("http.connection.timeout", 1000));

    public synchronized static Response get(String endpoint) {
        return
                given()
                        .config(config)
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(apiBaseAddress + endpoint)
                        .then()
                        .statusCode(200)
                        .extract().response();
    }

    public synchronized static Response post(String jsonBody, String endPoint) {
        if (endPoint.contains("update")) {
            statusCode = 200;
        } else {
            statusCode = 201;
        }
        return
                given()
                        .config(config)
                        .auth().basic(apiLoginUser.getUsername(), apiLoginUser.getPassword())
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .when()
                        .post(apiBaseAddress + endPoint)
                        .then()
                        //.log().all()  //DEBUG
                        .statusCode(statusCode)
                        .extract().response();
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static String updateValueInJson(String path, String parameter, String value) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File fileObj = new File(path);
            Map<String, Object> fileData = mapper.readValue(
                    fileObj, new TypeReference<>() {
                    });
            fileData.put(parameter, value);

            return mapper.writeValueAsString(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error in json update";
    }
}
