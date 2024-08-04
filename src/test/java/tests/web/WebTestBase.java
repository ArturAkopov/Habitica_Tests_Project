package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.APIConfig;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebTestBase {
    @BeforeAll
    static void beforeAll() {

        APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());
        RestAssured.baseURI = apiConfig.baseURI();
        RestAssured.basePath = apiConfig.basePath();

        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";

        if (System.getProperty("browserHost", "selenoid").equals("selenoid")) {
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.remote = webConfig.remote();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (System.getProperty("browserHost", "selenoid").equals("selenoid")) {
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
