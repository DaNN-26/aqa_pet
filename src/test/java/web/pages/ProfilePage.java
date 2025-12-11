package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final static SelenideElement userNameValue = $x("//label[@id='userName-value']");

    @Step("Получение имени пользователя")
    public String getUserName() {
        return userNameValue.getText();
    }
}
