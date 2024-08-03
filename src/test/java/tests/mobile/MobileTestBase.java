package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import tests.mobile.drivers.BrowserStackMobileDriver;
import tests.mobile.drivers.EmulateMobileDriver;
import tests.mobile.drivers.RealMobileDriver;

import static com.codeborne.selenide.Selenide.*;

public class MobileTestBase {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        if (System.getProperty("deviceHost", "browserstack").equals("real")) {
            Configuration.browser = RealMobileDriver.class.getName();
        } else if (System.getProperty("deviceHost", "browserstack").equals("emulation")) {
            Configuration.browser = EmulateMobileDriver.class.getName();
        }
        else if (System.getProperty("deviceHost", "browserstack").equals("browserstack")) {
            Configuration.browser = BrowserStackMobileDriver.class.getName();
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        Attach.pageSource();
        if ("browserstack".equals(System.getProperty("deviceHost", "browserstack"))) {
            Attach.addVideo(sessionId().toString());
        }
        closeWebDriver();
    }
}
