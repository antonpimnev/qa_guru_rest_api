package reqres;

import reqres.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static reqres.Specs.*;

public class ReqresInTestsExample {

    @Test
    @DisplayName("Создание пользователя")
    void createUserTest() {
        CreateUser data = new CreateUser();
        data.setName("Tester");
        data.setJob("Test-manager");

        given()
                .spec(request)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .spec(response201)
                .extract().as(UserData.class);
        assertEquals("Tester", data.getName());
        assertEquals("Test-manager", data.getJob());
    }

    @Test
    @DisplayName("Получение инфо пользователя по id")
    void getUserInfoByIdTest() {
        UserData data = given()
                .spec(request)
                .when()
                .get("/users/9")
                .then()
                .spec(response200)
                .log().body()
                .extract().as(UserData.class);
        assertEquals(9, data.getUser().getId());
        assertEquals("tobias.funke@reqres.in", data.getUser().getEmail());
    }

    @Test
    @DisplayName("Пользователь не найден")
    void getUserByIdNotFoundTest() {
        given()
                .spec(request)
                .when()
                .get("/users/999999")
                .then()
                .log().status()
                .log().body()
                .spec(response404);
    }

    @Test
    @DisplayName("Проверка получения списка пользователей")
    void listOfUsersWithGroovyTest(){
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("data.findAll{it.id}.last_name.flatten()",hasItem("Howell"))
                .body("data.findAll{it.id == 7}.email", hasItem("michael.lawson@reqres.in"));
    }

    @Test
    @DisplayName("Проверка обновления данных пользователя")
    void checkUpdateUser() {
        CreateUser data = new CreateUser();
        data.setName("TestBoss");
        data.setJob("Boss");

        given()
                .spec(request)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .spec(response200)
                .body("name", is("TestBoss"))
                .body("job", is("Boss"))
                .extract().as(UserData.class);
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserByIdTest() {
        given()
                .spec(request)
                .when()
                .delete("/users/5")
                .then()
                .log().status()
                .log().body()
                .spec(response204);
    }
}