package tests.web;

import config.AuthConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.web.pages.LoginPage;

@Tag("web")
@Owner("Акопов Артур")
@Feature("Authorisation")
@DisplayName("Проверки для авторизации")
public class AuthorisationTests extends WebTestBase {

    AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Проверка корректной авторизации c помощью имени пользователя")
    void checkSuccessfulAuthorisationWithUserName() {
        loginPage.openAuthorisationPage()
                .enterUserName(authConfig.login())
                .enterUserPassword(authConfig.password())
                .clickEnterButton()
                .checkVisibleMenuCollapse();
    }

    @Test
    @DisplayName("Проверка корректной авторизации c помощью email")
    void checkSuccessfulAuthorisationWithUserEmail() {
        loginPage.openAuthorisationPage()
                .enterUserName(authConfig.email())
                .enterUserPassword(authConfig.password())
                .clickEnterButton()
                .checkVisibleMenuCollapse();
    }
}
