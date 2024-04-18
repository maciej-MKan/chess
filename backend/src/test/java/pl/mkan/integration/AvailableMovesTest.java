package pl.mkan.integration;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static pl.mkan.controller.rest.GameController.API_PATH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AvailableMovesTest {
    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardPawnConfig"})
    public void getPawnAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardRookConfig"})
    public void getRookAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardKnightConfig"})
    public void getKnightAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardBishopConfig"})
    public void getBishopAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardQueenConfig"})
    public void getQueenAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForAvailableMoves#boardKingConfig"})
    public void getKingAvailableMoves(String requestBody, int expectedMovesCount) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves");

        if (expectedMovesCount > 0)
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", hasSize(expectedMovesCount));
        else {
            response
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("availableMoves.11", nullValue());
        }
    }

}
