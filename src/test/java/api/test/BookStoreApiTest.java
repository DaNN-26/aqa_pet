package api.test;

import api.steps.BookStoreSteps;
import model.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("apiTests")
public class BookStoreApiTest {
    private static final String URL = "https://demoqa.com/";

    @Owner("DaNN")
    @Description("Проверка авторизации через api")
    @Test
    public void checkAuthorization() {
        BookStoreSteps bookStoreSteps = new BookStoreSteps(URL);
        boolean success = bookStoreSteps.authorize(new UserData("Danek26", "DaneK2611$"));

        Assertions.assertTrue(success);
    }
}
