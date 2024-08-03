package tests.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    SelenideElement userName = $("#usernameInput"),
            userPassword = $("#passwordInput"),
            submitButton = $(".btn-info"),
            menuCollapse = $("#menu_collapse");

    @Step("Открытие страницы авторизации")
    public LoginPage openAuthorisationPage() {
        open("/login");
        return this;
    }

    @Step("Ввод логина {login}")
    public LoginPage enterUserName(String login) {
        userName.setValue(login);
        return this;
    }

    @Step("Ввод пароля {password}")
    public LoginPage enterUserPassword(String password) {
        userPassword.setValue(password);
        return this;
    }

    @Step("Нажатие кнопки \"Вход\"")
    public LoginPage clickEnterButton() {
        submitButton.click();
        return this;
    }

    @Step("Проверка видимости панели задач авторизованного пользователя")
    public void checkVisibleMenuCollapse() {
        menuCollapse.shouldBe(visible);
    }

}
