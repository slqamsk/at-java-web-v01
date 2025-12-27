package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    SelenideElement
        usernameInput = $("#username"),
        passwordInput = $("#password"),
        loginButton = $x("//button[@type='submit']"),
        flashMessage = $("#flash");

    @Step("Вход в систему")
    public void login(String username, String password) {
        this.usernameInput.setValue(username);
        this.passwordInput.setValue(password);
        this.loginButton.click();
    }

    @Step("Проверка успешного входа")
    public void verifySuccessfulLogin() {
        this.flashMessage.shouldBe(visible);
        this.flashMessage.shouldHave(text("You logged into a secure area"));
    }

    @Step("Проверка ошибки входа")
    public void verifyLoginError() {
        this.flashMessage.shouldBe(visible);
        // Проверяем наличие текста об ошибке (может быть разный текст)
        String messageText = this.flashMessage.getText();
        assert messageText.contains("invalid") || 
               messageText.contains("incorrect") ||
               messageText.contains("Your username") ||
               messageText.contains("error") : 
               "Должно быть сообщение об ошибке";
    }
}
