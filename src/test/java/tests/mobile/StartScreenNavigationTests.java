package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.mobile.screens.AuthorisationScreen;
import tests.mobile.screens.MainNavigationScreen;
import tests.mobile.screens.RegistrationScreen;
import tests.mobile.screens.WelcomeScreen;

@Tag("mobile")
@Owner("Акопов Артур")
@Feature("StartScreenNavigation")
@DisplayName("Проверки кнопок навигации начальных экранов")
public class StartScreenNavigationTests extends MobileTestBase {

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    MainNavigationScreen mainNavigationScreen = new MainNavigationScreen();
    AuthorisationScreen authorisationScreen = new AuthorisationScreen();
    RegistrationScreen registrationScreen = new RegistrationScreen();

    @Test
    @DisplayName("Проверка кнопки пропуска приветственного экрана - \"skip\"")
    void checkSkipButton() {
        welcomeScreen.
                clickSkipButton().
                checkAbsenceWelcomeMessage();
    }

    @Test
    @DisplayName("Проверка действия кнопки перехода к авторизации - \"LOGIN\"")
    void checkLoginButton() {
        welcomeScreen.clickSkipButton();
        mainNavigationScreen.clickLoginButton();
        authorisationScreen.checkVisibleAuthorisationFields();

    }

    @Test
    @DisplayName("Проверка действия кнопки перехода к регистрации - \"REGISTER\"")
    void checkRegisterButton() {
        welcomeScreen.clickSkipButton();
        mainNavigationScreen.clickRegisterButton();
        registrationScreen.checkVisibleRegistrationFields();

    }

}
