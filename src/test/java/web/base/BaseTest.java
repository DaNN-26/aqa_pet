package web.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    private static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.headless = true;
        Configuration.timeout = 20000;
        Configuration.pageLoadStrategy = "none";
        Configuration.pageLoadTimeout = 0;
        Configuration.baseUrl = "http://localhost:8090";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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
