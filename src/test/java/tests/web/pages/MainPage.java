package tests.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class MainPage {

    ElementsCollection menuCollapse = $(".navbar-collapse").$$("li"),
            taskTitle = $$(".task-title");
    SelenideElement habit = $$(".quick-add").get(0),
            task = $$(".quick-add").get(2);

    @Step("Открытие главной страницы")
    public MainPage openMainPage() {
        open("");
        return this;
    }

    @Step("Проверка Url")
    public MainPage checkCurrentUrl(String url) {
        webdriver().shouldHave(currentFrameUrl(url));
        return this;
    }

    @Step("Нажатие на кнопку раздела")
    public MainPage clickMenuElement(int value) {
        menuCollapse.get(value).click();
        return this;
    }

    @Step("Создание быстрой привычки {habit}")
    public MainPage createQuickHabit(String habit) {
        this.habit.setValue(habit).pressEnter();
        return this;
    }

    @Step("Создание быстрой задачи {task}")
    public MainPage createQuickTask(String task) {
        this.task.setValue(task).pressEnter();
        return this;
    }

    @Step("Проверка видимости привычки {habit} на главной странице")
    public void checkVisibleHabit(String habit) {
        taskTitle.findBy(Condition.text(habit)).shouldBe(visible);
    }

    @Step("Проверка видимости задачи {task} на главной странице")
    public void checkVisibleTask(String task) {
        taskTitle.findBy(Condition.text(task)).shouldBe(visible);
    }
}
