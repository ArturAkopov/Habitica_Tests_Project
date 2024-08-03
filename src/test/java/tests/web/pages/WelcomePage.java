package tests.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class WelcomePage {

    SelenideElement enterButton = $(".login-button"),
            joinButton = $(".join-button");

    @Step("Открытие приветственной страницы")
    public WelcomePage openWelcomePage() {
        open("");
        return this;
    }

    @Step("Нажатие кнопки \"Вход\"")
    public WelcomePage clickEnterButton() {
        enterButton.click();
        return this;
    }

    @Step("Нажатие кнопки \"Регистрация\"")
    public WelcomePage clickJoinButton() {
        joinButton.click();
        return this;
    }

    @Step("Проверка открытия экрана c {url}")
    public void checkOpenScreenWithRequiredUrl(String url) {
        webdriver().shouldHave(currentFrameUrl(url));
    }
}
