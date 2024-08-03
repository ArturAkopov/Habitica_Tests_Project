package tests.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class WelcomeScreen {

    SelenideElement skipButton = $(id("com.habitrpg.android.habitica:id/skipButton")),
            welcomeMessage = $(id("com.habitrpg.android.habitica:id/subtitleTextView"));

    @Step("Нажатие на кнопку Skip")
    public WelcomeScreen clickSkipButton() {
        skipButton.click();
        return this;
    }

    @Step("Проверка отсутствия приветствия на экране")
    public void checkAbsenceWelcomeMessage() {
        welcomeMessage.shouldNotBe(exist);
    }

}
