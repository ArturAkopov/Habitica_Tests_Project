package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.web.pages.WelcomePage;


@Tag("web")
@Owner("Акопов Артур")
@Feature("WelcomeScreenNavigation")
@DisplayName("Проверка кнопок навигации страницы приветствия")
public class WelcomePageNavigationTests extends WebTestBase {

    WelcomePage welcomePage = new WelcomePage();

    @Test
    @DisplayName("Проверка действия кнопки перехода к авторизации - \"Вход\"")
    void checkEnterButtonTest() {
        welcomePage.openWelcomePage()
                .clickEnterButton()
                .checkOpenScreenWithRequiredUrl("https://habitica.com/login");
    }

    @Test
    @DisplayName("Проверка действия кнопки перехода к регистрации - \"Регистрация\"")
    void checkJoinButtonTest() {
        welcomePage.openWelcomePage()
                .clickJoinButton()
                .checkOpenScreenWithRequiredUrl("https://habitica.com/register");
    }
}
