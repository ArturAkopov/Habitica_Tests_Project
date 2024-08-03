package tests.mobile;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.mobile.screens.WelcomeScreen;

@Tag("mobile")
@Owner("Акопов Артур")
@Feature("StartScreenNavigation")
@DisplayName("Проверки кнопок навигации начальных экранов")
public class StartScreenNavigationTests extends TestBase {

    @Test
    @DisplayName("Проверка кнопки пропуска приветственного экрана - \"skip\"")
    void checkIntroScreenTest() {

        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.
                clickSkipButton().
                checkAbsenceWelcomeMessage();
    }
}
