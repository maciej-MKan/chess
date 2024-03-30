package pl.mkan.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static pl.mkan.controller.rest.GameController.API_PATH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApiTest {
    @LocalServerPort
    private int port;

    @Test
    public void getNewGame() {
        given()
                .when()
                .get("http://localhost:" + port + API_PATH + "/game")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("pieces.size()", equalTo(32))
                .body("pieces.find { it.id == 11 }.type", equalTo("ROOK"))
                .body("pieces.find { it.id == 12 }.color", equalTo("WHITE"))
                .body("pieces.find { it.id == 13 }.position.row", equalTo(0))
                .body("pieces.find { it.id == 13 }.position.column", equalTo(2));
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigurationsForAvailableMoves#boardPawnConfig" })
    public void getAvailableMoves(String requestBody, int expectedMovesCount) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/available_moves")
                .then()
                .statusCode(200)
                .log().body()
                .body("availableMoves.11.size()", equalTo(expectedMovesCount));
    }

}
