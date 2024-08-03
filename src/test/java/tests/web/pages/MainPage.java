package tests.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class MainPage {

    ElementsCollection menuCollapse = $(".navbar-collapse").$$("li");

    @Step("Открытие главной страницы")
    public MainPage openMainPage(){
        open("");
        return this;
    }

    @Step("Проверка Url")
    public MainPage checkCurrentUrl(String url){
        webdriver().shouldHave(currentFrameUrl(url));
        return this;
    }

    @Step("Нажатие на кнопку раздела")
    public MainPage clickMenuElement(int value){
        menuCollapse.get(value).click();
        return this;
    }
}
