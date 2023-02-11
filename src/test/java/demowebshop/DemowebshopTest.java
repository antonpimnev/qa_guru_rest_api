package demowebshop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DemowebshopTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    String cookieValue = "33854D35CF77C513341C8C4CA3DACE6A08D67F5C8BF7F96BF7FBF"
        +"6D510B584489472BE58528C1DFE161F32D20D559462F424BF593F074B38E0BEE91943A"
        +"2476C844A3B8472C0CC081FF817388CB5346055D13BA183E459B53A3D567CAABD911F51"
        +"D8B0BE3C2AAD66119ED5A7D7BE4B8AFD4466261F1E324C686802584257D2678872C75ED"
        +"4B07E2084718EE582C7D9847B50802CB10286EABC29A7C0BBDBCA4B3E42F09B7F6B687DF3B6555847646803";
    String answerId = "pollAnswerId=2";
    String email = "email=tester.pimnev@gmail.com";
    String emailInvalid = "======gmail.com";
    String body = "product_attribute_72_5_18=53"
            +"&product_attribute_72_6_19=55"
            +"&product_attribute_72_3_20=58"
            +"&product_attribute_72_8_30=94"
            +"&addtocart_72.EnteredQuantity=1";
    String bodyCard = "giftcard_2.RecipientName=Anna&giftcard_2.RecipientEmail=anna%"
        +"40mail.ru&giftcard_2.SenderName=Anton+Pimnev&giftcard_2.SenderEmail=tester."
        +"pimnev%40gmail.com&giftcard_2.Message=&addtocart_2.EnteredQuantity=1";

    @Test
    @DisplayName("Проверка голосования для незарегистрированного пользователя")
    void voteUnregisteredUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(answerId)
                .when()
                .post("/poll/vote")
                .then().log().all()
                .statusCode(200)
                .body("error", is("Only registered users can vote."));
    }

    @Test
    @DisplayName("Проверка статус кода ответа в голосовании для зарегистрированного пользователя")
    void voteRegisteredUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(answerId)
                .when()
                .post("/poll/vote")
                .then().log().all()
                .statusCode(200)
                .body("error", not("Only registered users can vote."));
    }

    @Test
    @DisplayName("Проверка подписки на новости с невалидным email")
    void subscribeWithInvalidEmailTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(emailInvalid)
                .when()
                .post("/subscribenewsletter")
                .then().log().all()
                .statusCode(200)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }

    //По непонятной мне причине данный тест не хочет проходить. Там какие-то сложносоставные куки похоже нужно добавлять :(
    @Test
    @DisplayName("Проверка подписки на новости с валидным email")
    void subscribeEmailTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(email)
                .when()
                .post("/subscribenewsletter")
                .then().log().all()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину для незарегистрированного пользователя")
    void addToCartAnonymousTest() {
        String body = "product_attribute_72_5_18=65" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&product_attribute_72_8_30=94" +
                "&addtocart_72.EnteredQuantity=3";
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(3)"));
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину для зарегистрированного пользователя")
    void addToCartTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    @DisplayName("Проверка добавления в корзину подарочной карты для зарегистрированного пользователя")
    void  addGiftCardToCartTest(){
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH",cookieValue)
                .body(bodyCard)
                .when()
                .post("/addproducttocart/details/2/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }
}