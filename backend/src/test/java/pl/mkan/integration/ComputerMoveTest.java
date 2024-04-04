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
import static pl.mkan.controller.rest.GameController.API_PATH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ComputerMoveTest {
    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForComputerMove#coverInFirstMove"})
    public void coverInFirstMove(String requestBody, int movedId, int expectedRow, int expectedCol) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game")
                .then()
                .statusCode(200)
                .body("pieces.find { it.id ==" + movedId + " }.moved", equalTo(true))
                .body("pieces.find { it.id ==" + movedId + " }.position.row", equalTo(expectedRow))
                .body("pieces.find { it.id ==" + movedId + " }.position.column", equalTo(expectedCol));
    }
}
