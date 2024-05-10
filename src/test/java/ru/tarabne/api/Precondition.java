package ru.tarabne.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class Precondition {
    /*Предзаполняем корзину двумя товарами*/
    public void populateCart() {
        open("https://www.maxidom.ru");
        Set<Cookie> cookies = getWebDriver().manage().getCookies();
        Map<String, String> cookieMap = new HashMap<>();

        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
        }
        RestAssured.baseURI = "https://www.maxidom.ru";
        given()
                .cookies(cookieMap)
                .when()
                .get("/ajax/basket/addBasket.php?quantity=1&id=6747076")
                .then();

        given()
                .cookies(cookieMap)
                .when()
                .get("/ajax/basket/addBasket.php?quantity=1&id=10318010")
                .then();
    }
}