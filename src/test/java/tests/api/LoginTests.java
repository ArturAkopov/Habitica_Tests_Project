package tests.api;

import authorization.model.AuthResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("api")
@DisplayName("Проверки авторизации")
public class LoginTests extends TestBase {

    TestSteps steps = new TestSteps();

    @Test
    @DisplayName("Проверка корректной авторизации")
    void authorizationSuccessTest() {
        AuthResponseBodyModel response = steps.getAuthorization();
        steps.checkStatusForAuth(response);
        steps.checkUserIdForAuth(response);
        steps.checkApiTokenForAuth(response);
        steps.checkUserNameForAuth(response);
    }

    @Test
    @DisplayName("Проверка невозможности авторизации без пароля")
    void authorizationNoSuccessTest() {
        AuthResponseBodyModel response = steps.getBadAuthResponse();
        steps.checkBadStatusForAuth(response);
        steps.checkMessageErrorForAuth(response);
        steps.checkClarifyingMessageErrorsForAuth(response);
    }

}
