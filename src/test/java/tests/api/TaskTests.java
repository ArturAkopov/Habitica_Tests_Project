package tests.api;

import authorization.model.AuthResponseBodyModel;
import model.GetUserResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("api")
@DisplayName("Проверки взаимодействия с задачами")
public class TaskTests extends TestBase {

    TestSteps steps = new TestSteps();

    @Test
    @DisplayName("Проверка успешного получения информации о списках задач")
    void getUserTasksTest() {
        AuthResponseBodyModel authResponse = steps.getAuthorization();
        GetUserResponseBodyModel response = steps.getUserTasks(authResponse);
        steps.checkTasksOrderList(authResponse,response);
    }

}
