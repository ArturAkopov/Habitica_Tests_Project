package tests.api;

import authorization.Authorization;
import authorization.model.AuthRequestBodyModel;
import authorization.model.AuthResponseBodyModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.AuthConfig;
import io.qameta.allure.Step;
import tests.api.model.DeleteTaskUserResponseBodyModel;
import tests.api.model.GetUserResponseBodyModel;
import tests.api.model.PostCreateTaskUserRequestModel;
import tests.api.model.PostCreateTaskUserResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.Assertions;

import java.io.File;
import java.io.IOException;

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
        return given(requestSpec)
                .when()
                .header("X-Api-Key", authResponse.getData().getApiToken())
                .header("X-Api-User", authResponse.getData().getId())
                .get("/user")
                .then()
                .spec(responseSpec200)
                .extract().as(GetUserResponseBodyModel.class);
    }

    @Step("Создание новой задачи для пользователя {authResponse.data.username}")
    public PostCreateTaskUserResponseModel createNewTask(AuthResponseBodyModel authResponse, String setText, String setId) throws IOException {

        ObjectMapper om = new ObjectMapper();
        PostCreateTaskUserRequestModel[] request = om.readValue(
                new File("src/test/resources/request/PostCreateTaskUser.json"),
                PostCreateTaskUserRequestModel[].class
        );
        request[0].setText(setText);
        request[0].set_id(setId);

        return given(requestSpec)
                .when()
                .body(request)
                .header("X-Api-Key", authResponse.getData().getApiToken())
                .header("X-Api-User", authResponse.getData().getId())
                .post("/tasks/user")
                .then()
                .spec(responseSpec201)
                .extract().as(PostCreateTaskUserResponseModel.class);
    }

    @Step("Удаление задачи {id} для пользователя {authResponse.data.username}")
    public DeleteTaskUserResponseBodyModel deleteUserTask(AuthResponseBodyModel authResponse,String id) {
        return given(requestSpec)
                .when()
                .header("X-Api-Key", authResponse.getData().getApiToken())
                .header("X-Api-User", authResponse.getData().getId())
                .get("/tasks/"+id)
                .then()
                .spec(responseSpec200)
                .extract().as(DeleteTaskUserResponseBodyModel.class);
    }

    @Step("Проверка статуса success = false для неуспешной авторизации")
    public void checkBadStatusForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getSuccess()).isEqualTo(false);
    }

    @Step("Проверка сообщения об ошибке message = \"Invalid request parameters.\" для неуспешной авторизации")
    public void checkMessageErrorForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getMessage()).contains("Invalid request parameters.");
    }

    @Step("Проверка уточняющего сообщения об ошибке errors.message = \"Missing password.\" для неуспешной авторизации")
    public void checkClarifyingMessageErrorsForAuth(AuthResponseBodyModel response) {
        Assertions.assertThat(response.getErrors().get(0).getMessage()).contains("Missing password.");
    }

    @Step("Проверка статуса success = true ")
    public void checkStatus(Boolean status) {
        Assertions.assertThat(status).isEqualTo(true);
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

    @Step("Проверка успешного создания задачи")
    public void checkSuccessfulCreateTask(PostCreateTaskUserResponseModel response, String setText, String setId) {
        Assertions.assertThat(response.getSuccess()).isTrue();
        Assertions.assertThat(response.getData().getText()).isEqualTo(setText);
        Assertions.assertThat(response.getData().get_id()).isEqualTo(setId);
    }
}
