package reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresInTestsExample {

    @Test
    @DisplayName("Создание пользователя")
    void createUserTest() {
        String body = "{ \"name\": \"Tester\", \"job\": \"Test-manager\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Tester"))
                .body("job", is("Test-manager"));
    }

    @Test
    @DisplayName("Пользователь не создается если не передать body запроса")
    void createUserFailTest() {
        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    @DisplayName("Получение инфо пользователя по id")
    void getUserInfoByIdTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/9")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.last_name", is("Funke"),
                        "data.email", is("tobias.funke@reqres.in"),
                        "support.url", is("https://reqres.in/#support-heading"));
    }

    @Test
    @DisplayName("Пользователь не найден")
    void getUserByIdNotFoundTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/999999")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Login пользователя")
    void loginTest() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserByIdTest() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/5")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}