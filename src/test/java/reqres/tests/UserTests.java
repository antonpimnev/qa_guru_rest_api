package reqres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reqres.models.CreateUser;
import reqres.models.UserData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reqres.data.Data.*;
import static reqres.data.Endpoints.API_USERS;
import static reqres.specs.Specs.request;
import static reqres.specs.Specs.response;

@Tag("api")
public class UserTests {
    @Test
    @DisplayName("Создание пользователя")
    void createUserTest() {
        CreateUser data = new CreateUser();
        data.setName(NEW_USER_NAME);
        data.setJob(NEW_USER_JOB);

        given()
                .spec(request)
                .body(data)
                .when()
                .post(API_USERS)
                .then()
                .spec(response)
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
                .get(API_USERS + USER_FOR_INFO)
                .then()
                .spec(response)
                .extract().as(UserData.class);
        assertEquals(USER_FOR_INFO, data.getUser().getId());
        assertEquals(USER_FOR_INFO_EMAIL, data.getUser().getEmail());
    }

    @Test
    @DisplayName("Проверка обновления данных пользователя")
    void checkUpdateUser() {
        CreateUser data = new CreateUser();
        data.setName(USER_FOR_UPDATE_NAME);
        data.setJob(USER_FOR_UPDATE_JOB);

        given()
                .spec(request)
                .body(data)
                .when()
                .put(API_USERS + USER_FOR_UPDATE_ID)
                .then()
                .spec(response)
                .statusCode(200)
                .body("name", is(USER_FOR_UPDATE_NAME))
                .body("job", is(USER_FOR_UPDATE_JOB))
                .extract().as(UserData.class);
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserByIdTest() {
        given()
                .spec(request)
                .when()
                .delete(API_USERS + USER_FOR_DELETE)
                .then()
                .spec(response)
                .statusCode(204);
    }
}
