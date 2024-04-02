package pl.mkan.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static pl.mkan.controller.rest.GameController.API_PATH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GameOverTest {
    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigurationForGameOver#boardWithGameOver" })
    public void assertGameIsOver(String requestBody, boolean isGameOver, String winner) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/game_over")
                .then()
                .statusCode(200)
                .log().body()
                .body("isGameOver", equalTo(isGameOver))
                .body("winner", equalTo(winner));
    }

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigurationForGameOver#boardWithOutGameOver" })
    public void assertGameNotOver(String requestBody, boolean isGameOver) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/game_over")
                .then()
                .statusCode(200)
                .log().body()
                .body("isGameOver", equalTo(isGameOver))
                .body("winner", nullValue());
    }
}
