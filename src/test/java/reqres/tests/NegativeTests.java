package reqres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static reqres.data.Endpoints.API_UNKNOWN;
import static reqres.specs.Specs.request;

@Tag("api")
public class NegativeTests {
    @Test
    @DisplayName("Пользователь не найден")
    void getUserByIdNotFoundTest() {
        given()
                .spec(request)
                .when()
                .get(API_UNKNOWN)
                .then()
                .statusCode(404);
    }
}
