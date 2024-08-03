package tests.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class MainNavigationScreen {

    SelenideElement registerButton = $(id("com.habitrpg.android.habitica:id/new_game_button")),
            loginButton = $(id("com.habitrpg.android.habitica:id/show_login_button"));

    @Step("Нажатие кнопки REGISTER")
    public MainNavigationScreen clickRegisterButton() {
        registerButton.click();
        return this;
    }

    @Step("Нажатие кнопки LOGIN")
    public MainNavigationScreen clickLoginButton() {
        loginButton.click();
        return this;
    }


}
