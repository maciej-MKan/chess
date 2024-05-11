package pl.mkan.integration;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static pl.mkan.controller.rest.GameController.API_PATH;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PawnPromotionTest {
    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource({"pl.mkan.helper.BoardConfigForPawnPromotion#pawnPromotion"})
    public void shouldDetectPawnPromotion(String requestBody, boolean isPromotion) {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + API_PATH + "/game/state")
                .then()
                .statusCode(200)
                .log().body()
                .body("pawnPromotion.pawn", isPromotion ? Matchers.notNullValue() : Matchers.nullValue())
                .body("pawnPromotion.figuresToPromote.size()", isPromotion ? Matchers.equalTo(4) : Matchers.equalTo(0));
    }
}
