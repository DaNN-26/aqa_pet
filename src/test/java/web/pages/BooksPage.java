package web.pages;

import web.model.BookData;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;

public class BooksPage {
    private final static ElementsCollection bookRows = $$x("//div[@class='rt-tr-group']");

    public static BooksPage openBooksPage(String url) {
        Selenide.open(url + "books");
        return new BooksPage();
    }

    @Step("Получение списка книг")
    public List<BookData> getBooks() {
        Selenide.$x("//div[@class='rt-tbody']").shouldBe(visible);
        return bookRows.stream().map(this::parseBookRow).toList();
    }

    private BookData parseBookRow(SelenideElement row) {
        ElementsCollection data = row.$$x(".//div[@class='rt-td']");
        return new BookData(
                data.get(1).getText(),
                data.get(2).getText(),
                data.get(3).getText()
        );
    }
}
