package tests.api;

import authorization.Authorization;
import authorization.model.AuthRequestBodyModel;
import authorization.model.AuthResponseBodyModel;
import config.AuthConfig;
import io.qameta.allure.Step;
import model.GetUserResponseBodyModel;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.Assertions;

import static io.restassured.RestAssured.given;
import static spec.HabiticaSpec.*;

public class TestSteps {

    AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Step("Авторизация пользователя")
    public AuthResponseBodyModel getAuthorization() {
        return Authorization.getAuthResponse();
    }


    @Step("Авторизация пользователя с password=\" \"")
    public AuthResponseBodyModel getBadAuthResponse() {

        AuthRequestBodyModel request = new AuthRequestBodyModel();
        request.setUsername(authConfig.login());
        request.setPassword("");
        return given(requestSpec)
                .body(request)
                .when()
                .post("/user/auth/local/login")
                .then()
                .spec(responseSpec400)
                .extract().as(AuthResponseBodyModel.class);
    }

    @Step("Получение задач для пользователя {authResponse.data.username}")
    public GetUserResponseBodyModel getUserTasks(AuthResponseBodyModel authResponse) {
        return  given(requestSpec)
                .when()
                .header("X-Api-Key", authResponse.getData().getApiToken())
                .header("X-Api-User", authResponse.getData().getId())
                .get("/user")
                .then()
                .spec(responseSpec200)
                .extract().as(GetUserResponseBodyModel.class);
    }

    @Step("Проверка статуса success = {response.success} для неуспешной авторизации")
    public void checkBadStatusForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getSuccess()).isEqualTo(false);
    }

    @Step("Проверка сообщения об ошибке message = {response.message} для неуспешной авторизации")
    public void checkMessageErrorForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getMessage()).contains("Invalid request parameters.");
    }

    @Step("Проверка уточняющего сообщения об ошибке errors.message = {response.errors.message} для неуспешной авторизации")
    public void checkClarifyingMessageErrorsForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getErrors().get(0).getMessage()).contains("Missing password.");
    }

    @Step("Проверка статуса success = {response.success} для успешной авторизации")
    public void checkStatusForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getSuccess()).isEqualTo(true);
    }

    @Step("Проверка id = {response.data.id} авторизованного пользователя")
    public void checkUserIdForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getData().getId()).contains(authConfig.userId());
    }

    @Step("Проверка apiToken = {response.data.apiToken} авторизованного пользователя")
    public void checkApiTokenForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getData().getApiToken()).contains(authConfig.apiToken());
    }

    @Step("Проверка username = {response.data.username} авторизованного пользователя")
    public void checkUserNameForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getData().getUsername()).contains(authConfig.login());
    }

    @Step("Проверка успешного получения списка задач")
    public void checkTasksOrderList(AuthResponseBodyModel authResponse, GetUserResponseBodyModel response) {
        Assertions.assertThat(response.getSuccess()).isEqualTo(true);
        Assertions.assertThat(response.getData().getId()).isEqualTo(authResponse.getData().getId());
        Assertions.assertThat(response.getData().getTasksOrder().getHabits()).isNotNull();
        Assertions.assertThat(response.getData().getTasksOrder().getTodos()).isNotNull();
        Assertions.assertThat(response.getData().getTasksOrder().getDailys()).isNotNull();
        Assertions.assertThat(response.getData().getTasksOrder().getHabits()).isNotNull();
    }
}
