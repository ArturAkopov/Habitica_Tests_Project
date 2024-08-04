package tests.web;

import extensions.WithLogin;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.web.pages.MainPage;
import utils.MainPageMenuItems;
import utils.TestData;

@Tag("web")
@Owner("Акопов Артур")
@Feature("User actions on the main page")
@DisplayName("Проверки действий пользователя на главной странице")
public class MainPageTests extends WebTestBase {

    TestData data = new TestData();
    String habitName = data.taskName;
    String taskName = data.taskName;
    MainPage mainPage = new MainPage();
    MainPageMenuItems[] mainPageChapters = MainPageMenuItems.values();

    @Test
    @WithLogin
    @DisplayName("Проверка перемещения по разделам главной страницы")
    void mainPageNavigationTest() {
        mainPage.openMainPage();
        for (MainPageMenuItems item : mainPageChapters) {
            mainPage.clickMenuElement(item.getValue())
                    .checkCurrentUrl(item.getLink());
        }
    }

    @Test
    @WithLogin
    @DisplayName("Проверка быстрого создания задачи на главной странице")
    void quickCreateHabitTest() {
        mainPage.openMainPage()
                .createQuickHabit(habitName)
                .checkVisibleHabit(habitName);
    }

    @Test
    @WithLogin
    @DisplayName("Проверка быстрого создания задачи на главной странице")
    void quickCreateTaskTest() {
        mainPage.openMainPage()
                .createQuickTask(taskName)
                .checkVisibleTask(taskName);
    }

}
