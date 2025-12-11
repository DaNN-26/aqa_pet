package web.pages;

import model.UserData;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final static SelenideElement userNameField = $x("//input[@placeholder='UserName']");
    private final static SelenideElement passwordField = $x("//input[@placeholder='Password']");
    private final static SelenideElement loginButton = $x("//button[@id='login']");
    private final static SelenideElement errorMessage = $x("//p[@id='name' and contains(text(),'Invalid username or password')]");

    @Step("Открытие страницы авторизации")
    public static LoginPage openLoginPage(String url) {
        Selenide.open(url + "login");
        return new LoginPage();
    }

    @Step("Авторизация с верными данными")
    public ProfilePage authorize(UserData user) {
        userNameField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5));

        wait.until(d-> errorMessage.isDisplayed() || d.getCurrentUrl().contains("profile"));

        if (errorMessage.isDisplayed()) {
            throw new RuntimeException("Login Failed");
        }

        return new ProfilePage();
    }
}
