package tests.api;

import authorization.model.AuthResponseBodyModel;
import model.GetUserResponseBodyModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static spec.HabiticaSpec.requestSpec;
import static spec.HabiticaSpec.responseSpec200;

@Tag("api")
@DisplayName("Проверки взаимодействия с задачами")
public class TaskTests extends TestBase {

    TestSteps steps = new TestSteps();

    @Test
    @DisplayName("Проверка успешного получения информации о списках задач")
    void getUserTasksTest() {
        AuthResponseBodyModel authResponse = steps.getAuthorization();
        GetUserResponseBodyModel response = given(requestSpec)
                .when()
                .header("X-Api-Key", authResponse.getData().getApiToken())
                .header("X-Api-User", authResponse.getData().getId())
                .get("/user")
                .then()
                .spec(responseSpec200)
                .extract().as(GetUserResponseBodyModel.class);
        Assertions.assertThat(response.getData().getTasksOrder().getDailys()).isEmpty();
        Assertions.assertThat(response.getData().getTasksOrder().getTodos()).isNotEmpty();

    }

}
