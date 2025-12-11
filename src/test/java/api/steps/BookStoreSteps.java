package api.steps;

import api.specs.BookStoreSpecs;
import model.UserData;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class BookStoreSteps {
    private final String url;

    public BookStoreSteps(String url) {
        this.url = url;
    }

    @Step("Авторизация")
    public boolean authorize(UserData userData) {
        try {
            given()
                    .spec(BookStoreSpecs.requestSpecification(url))
                    .body(userData)
                    .when()
                    .post("Account/v1/Authorized")
                    .then()
                    .statusCode(200);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}
