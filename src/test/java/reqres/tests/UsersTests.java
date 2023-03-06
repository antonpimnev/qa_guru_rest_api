package reqres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static reqres.data.Data.*;
import static reqres.data.Endpoints.API_LIST_USERS;
import static reqres.specs.Specs.request;
import static reqres.specs.Specs.response;

@Tag("api")
public class UsersTests {
    @Test
    @DisplayName("Проверка получения списка пользователей")
    void listOfUsersWithGroovyTest(){
        given()
                .spec(request)
                .when()
                .get(API_LIST_USERS)
                .then()
                .spec(response)
                .body("data.findAll{it.id}.last_name.flatten()",hasItem(USERS_LIST_NAME))
                .body("data.findAll{it.id == " + ID + "}.email", hasItem(USERS_LIST_EMAIL));
    }
}
