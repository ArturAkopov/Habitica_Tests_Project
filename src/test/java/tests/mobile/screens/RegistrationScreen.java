package tests.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class RegistrationScreen {

    SelenideElement userNameFiled = $(id("com.habitrpg.android.habitica:id/username")),
            emailAddressField = $(id("com.habitrpg.android.habitica:id/email")),
            passwordField = $(id("com.habitrpg.android.habitica:id/password")),
            registerButton = $(id("com.habitrpg.android.habitica:id/login_btn"));

    @Step("Проверка наличия полей для регистрации на экране")
    public void checkVisibleRegistrationFields() {
        userNameFiled.shouldBe(visible);
        emailAddressField.shouldBe(visible);
        passwordField.shouldBe(visible);
        registerButton.shouldBe(visible);
    }

}
