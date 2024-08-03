package tests.api;

import authorization.model.AuthResponseBodyModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import tests.api.model.DeleteTaskUserResponseBodyModel;
import tests.api.model.GetUserResponseBodyModel;
import tests.api.model.PostCreateTaskUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.TestData;

import java.io.IOException;



@Tag("api")
@Owner("Акопов Артур")
@Feature("Actions with tasks")
@DisplayName("Проверки взаимодействия с задачами")
public class TasksTests extends TestBase {

    TestSteps steps = new TestSteps();
    TestData testData = new TestData();
    String text = testData.taskName;
    String id = testData.taskId;

    @Test
    @DisplayName("Проверка успешного получения информации о списках задач")
    void getUserTasksTest() {
        AuthResponseBodyModel authResponse = steps.getAuthorization();
        GetUserResponseBodyModel response = steps.getUserTasks(authResponse);
        steps.checkTasksOrderList(authResponse,response);
    }

    @Test
    @DisplayName("Проверка создания новой задачи")
    void createUserTaskTest() throws IOException {
        AuthResponseBodyModel authResponse = steps.getAuthorization();
        PostCreateTaskUserResponseModel response = steps.createNewTask(authResponse,text,id);
        steps.checkSuccessfulCreateTask(response,text,id);
    }

    @Test
    @DisplayName("Проверка удаления задачи")
    void deleteUserTaskTest() throws IOException {
        AuthResponseBodyModel authResponse = steps.getAuthorization();
        PostCreateTaskUserResponseModel taskResponse = steps.createNewTask(authResponse,text,id);
        DeleteTaskUserResponseBodyModel response = steps.deleteUserTask(authResponse,taskResponse.getData().get_id());
        steps.checkStatus(response.getSuccess());
    }

}
