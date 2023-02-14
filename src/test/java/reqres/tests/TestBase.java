package reqres.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static reqres.helpers.CustomApiListener.withCustomTemplates;

public class TestBase {
    @BeforeAll
    public static void setUp() {
//                .filter(withCustomTemplates())
//                .baseUri("https://reqres.in")
//                .basePath("/api");
        RestAssured.filters(withCustomTemplates());
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}