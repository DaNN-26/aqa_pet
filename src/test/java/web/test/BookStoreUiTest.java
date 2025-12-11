package web.test;

import model.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.base.BaseTest;
import web.pages.BooksPage;
import web.pages.LoginPage;
import web.pages.ProfilePage;

public class BookStoreUiTest extends BaseTest {
    private static final String URL = "https://demoqa.com/";

    @Owner("DaNN")
    @Description("Проверка величины списка книг")
    @Test
    public void checkBookListSize() {
        int expectedSize = 10;

        BooksPage booksPage = BooksPage.openBooksPage(URL);
        int actualSize = booksPage.getBooks().size();

        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Owner("DaNN")
    @Description("Проверка отображения UserName в профиле")
    @Test
    public void checkProfileUserName() {
        UserData user = new UserData("Danek26", "DaneK2611$");

        LoginPage loginPage = LoginPage.openLoginPage(URL);
        ProfilePage profilePage = loginPage.authorize(user);

        Assertions.assertEquals(user.getUserName(), profilePage.getUserName());
    }
}
