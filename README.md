# Проект по автоматизации тестирования для компании [<img width="20%" title="HABITICA" src="media/logo/habitica_logo.png"/>](https://habitica.com)
> Habitica — трекер задач, который совместил философию RPG и GTD(доведение дел до завершения) в своей Web-версии и приложениях для Android и iOS.

## :scroll: Содержание:
- [Особенности проекта](#особенности-проекта)
- [Используемый стек](#computer-используемый-стек)
- [Реализованные проверки](#ballot_box_with_check-реализованные-проверки)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-тестов-в-selenoid)

## Особенности проекта
- Проект состоит из UI-тестов, API и мобильных тестов на Android
- В данном проекте автотесты написаны на языке `Java`
- В качестве сборщика был использован - `Gradle`
- Использованы фреймворки `JUnit 5` и `Selenide`
- Шаблон проектирования `Page Object` 
- Использование техноголии `Owner` для придания тестам гибкости и легкости конфигурации
- Возможность запуска тестов: локально, удалённо, по тегам
- Использование `Faker` для генерации данных
- Использование `Lombok` для моделей в API тестах
- Использование собственных расширений:
    - `@WithLogin` для предварительной авторизации
    - `@LoggingExtension` для дополнительного логирования тестов
- При прогоне UI тестов браузер запускается в `Selenoid`
- Возможность запуска проектов с помощью `Jenkins`
- Возможность запуска тестов напрямую из `Allure TestOps`
- Интеграция с `Jira`
- Уведомление о результатах прохождения в `Telegram`
- По итогу прохождения автотестов генерируется `Allure` отчет. Содержание отчета:
    - Шаги теста
    - Скриншот страницы на последнем шаге
    - Исходный код страницы в браузере
    - Логи консоли браузера
    - Видео выполнения автотеста

## :computer: Используемый стек
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg"/></a> 
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg"/></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/logo/Gradle.svg"/></a> 
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="media/logo/Selenide.svg"/></a> 
<a href="https://aerokube.com/selenoid/"><img width="6%" title="Selenoid" src="media/logo/Selenoid.svg"/></a>
<a href="https://rest-assured.io"><img width="6%" title="REST-Assured" src="media/logo/RestAssured.svg"/></a>
<a href="https://github.com/allure-framework/allure2"><img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg"/></a>
<a href="https://qameta.io"><img width="6%" title="Allure TestOps" src="media/logo/Allure_TO.svg"/></a>
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="media/logo/JUnit5.svg"/></a> 
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/logo/GitHub.svg"/></a> 
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="media/logo/Jenkins.svg"/></a> 
<a href="https://telegram.org/"><img width="6%" title="Telegram" src="media/logo/Telegram.svg"/></a>  
<a href="https://app-automate.browserstack.com/"><img width="6%" title="BrowserStack" src="media/logo/Browserstack.svg"/></a>  
<a href="https://developer.android.com/studio"><img width="6%" title="Android Studio.svg" src="media/logo/Android_Studio.svg"/></a>   
<a href="https://appium.io"><img width="6%" title="Appium" src="media/logo/Appium.svg"/></a>  
<a href="https://www.atlassian.com/software/jira"><img width="6%" title="Jira" src="media/logo/Jira.svg"/></a>  
</p>

## :ballot_box_with_check: Реализованные проверки:

- Проверка содержания ссылки "https://www.it-one.ru/" в логотипе
- Проверка наименований разделов навигации в бургер-меню
- Проверка титульного названия раздела услуг компании
- Проверка титульного названия раздела вакансий компании
- Проверка работы кнопки "Компания" в разделе навигации в бургер-меню


## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/logo/Jenkins.svg"> Сборка в Jenkins

* <code>BROWSER</code> – браузер, в котором будут выполняться тесты. По умолчанию - <code>chrome</code>.
* <code>BROWSER_VERSION</code> – версия браузера, в которой будут выполняться тесты. По умолчанию - <code>121.0</code>.
* <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты. По умолчанию - <code>1920x1080</code>.
* <code>SELENOID_URL</code> – Url, адрес удаленного сервера, на котором будут запускаться тесты. По умолчанию - <code>selenoid.autotests.cloud</code>.

<a id="console"></a>
### Команды для запуска из терминала
___
***Локальный запуск:***
```bash
gradle clean test main_page_tests
-"Dbrowser=${BROWSER}"
-"Dsize=${BROWSER_SIZE}"
-"Dversion=${BROWSER_VERSION}"
-"Dselenoid=${SELENOID_URL}"
```
При выполнении данной команды в терминале IDE тесты запустятся удаленно в <code>Selenoid</code>.

***Удалённый запуск через Jenkins:***
```bash  
clean test main_page_tests
-"Dbrowser=${BROWSER}"
-"Dsize=${BROWSER_SIZE}"
-"Dversion=${BROWSER_VERSION}"
-"Dselenoid=${SELENOID_URL}"
```
Для запуска сборки необходимо перейти в раздел <code>Собрать с параметрами</code> и нажать кнопку <code>Собрать</code>.
<p align="center">
<img title="Jenkins Build" src="media/screens/jenkins.jpg">
</p>
После выполнения сборки, в блоке <code>История сборок</code> напротив номера сборки появятся значек <code>Allure Report</code>, при клике на который откроется страница с сформированным html-отчетом и тестовой документацией соответственно.

## <img width="4%" style="vertical-align:middle" title="Allure_Report" src="media/logo/Allure_Report.svg"> Пример Allure-отчета


## Основная страница отчёта

<p align="center">  
<img title="Allure Overview Dashboard" src="media/screens/allure1.jpg" width="850">  
</p>  

## Тест-кейсы

<p align="center">  
<img title="Allure Tests" src="media/screens/allure2.jpg" width="850">  
</p>

### <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screens/telegram.jpg">
</p>

### <img width="4%" style="vertical-align:middle" title="Selenoid" src="media/logo/Selenoid.svg"> Видео примера запуска тестов в Selenoid

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста
<p align="center">
  <img title="Selenoid Video" src="media/screens/selenoid.gif">
</p>
