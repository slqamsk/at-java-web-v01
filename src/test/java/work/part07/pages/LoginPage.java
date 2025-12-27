package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement
        username = $("#username"),
        password = $("#password"),
        loginButton = $("#loginButton"),
        errorMessage = $("#message"),
        greeting = $("#greeting");

    @Step("Вход в систему")
    public void login(String username, String password) {
        this.username.setValue(username);
        this.password.setValue(password);
        this.loginButton.click();
    }

    @Step("Вход в систему с помощью Enter")
    public void loginWithEnter(String username, String password) {
        this.username.setValue(username);
        this.password.setValue(password).pressEnter();
    }

    @Step("Вход с пустыми полями")
    public void loginWithEmptyFields() {
        this.loginButton.click();
    }

    @Step("Неуспешный логин")
    public void isLoginUnsuccessful() {
        this.errorMessage.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Step("Проверка сообщения о заблокированном пользователе")
    public void isUserBlocked() {
        // На этом сайте заблокированный пользователь возвращает обычное сообщение об ошибке
        this.errorMessage.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Step("Проверка сообщения о пустых полях")
    public void isEmptyFieldsError() {
        this.errorMessage.shouldHave(text("Username and Password are required."));
    }

    @Step("Успешный логин")
    public void isLoginSuccessful(String fio) {
        this.greeting.shouldHave(text("Добро пожаловать, " + fio + "!"));
    }

    @Step("Проверка отображения ФИО в шапке")
    public void verifyFIOInHeader(String fio) {
        this.greeting.shouldHave(text("Добро пожаловать, " + fio + "!"));
    }

}