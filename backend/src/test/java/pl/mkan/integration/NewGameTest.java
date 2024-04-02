package pl.mkan.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
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
public class NewGameTest {
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
}