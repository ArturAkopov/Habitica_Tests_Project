package tests.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class AuthorisationScreen {

    SelenideElement loginField = $(id("com.habitrpg.android.habitica:id/username")),
            passwordField = $(id("com.habitrpg.android.habitica:id/password")),
            loginButton = $(id("com.habitrpg.android.habitica:id/login_btn"));

    @Step("Проверка наличия полей для авторизации на экране")
    public void checkVisibleAuthorisationFields() {
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

}
