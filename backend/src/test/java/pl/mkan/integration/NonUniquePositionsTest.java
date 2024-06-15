package pl.mkan.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
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
public class NonUniquePositionsTest {
    @LocalServerPort
    private int port;

    @Disabled //ToDo fix validator
    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForNonUniquePiecesPosition#boardWithCoverPosition"})
    public void assertGameIsOver(String requestBody, String errorMessage) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game")
                .then()
                .statusCode(400)
                .log().body()
                .body("error", equalTo(errorMessage));
    }
}
