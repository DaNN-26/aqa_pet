package web.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    private static void setup() {
        Configuration.remote = "http://host.docker.internal:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        Configuration.browserCapabilities = options;

        Configuration.timeout = 20000;
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener(
                "AllureSelenide", new AllureSelenide()
                        .screenshots(true)
        );
    }

    @BeforeAll
    public static void init() {
        setup();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}