package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {
    SelenideElement
        flightInfo = $("#flightRegistrationInfo"),
        buttonFinishRegistration = $x("//button[contains(.,'Завершить регистрацию')]"),
        fio = $("#passengerName"),
        passport = $("#passportNumber"),
        email = $("#email"),
        phone = $("#phone"),
        message = $("#registrationMessage"),
        backButton = $x("//button[.='Вернуться к найденным рейсам']");

    @Step("Проверка, что данные рейса корректные")
    public void isFlightDataCorrect(String cityFrom, String cityTo) {
        flightInfo
                .shouldBe(visible)
                .shouldHave(text(cityFrom + " → " + cityTo));
    }

    @Step("Проверка автозаполнения данных пользователя")
    public void verifyAutoFill(String expectedFIO) {
        this.fio.shouldHave(text(expectedFIO));
    }

    @Step("Успешная регистрация со значениями по умолчанию")
    public void successDefaultRegistration() {
        buttonFinishRegistration.click();
        Alert alert= switchTo().alert();
        assertTrue(alert.getText().contains("Бронирование завершено"));
        alert.accept();
        this.message.shouldHave(text("Регистрация успешно завершена!"));
    }

    @Step("Заполнение полей и регистрация")
    public void registration(String fio, String passport, String email, String phone) {
        this.fio.setValue(fio);
        this.passport.setValue(passport);
        this.email.setValue(email);
        this.phone.setValue(phone);
        buttonFinishRegistration.click();
    }

    @Step("Регистрация с помощью Enter")
    public void registrationWithEnter(String fio, String passport, String email, String phone) {
        this.fio.setValue(fio);
        this.passport.setValue(passport);
        this.email.setValue(email);
        this.phone.setValue(phone).pressEnter();
    }

    @Step("Появилась ошибка Заполните все поля")
    public void isErrorFillAllFied() {
        this.message.shouldHave(text("Пожалуйста, заполните все поля."));
    }

    @Step("Проверка ошибки валидации паспорта")
    public void isPassportValidationError() {
        this.message.shouldHave(text("Номер паспорта должен содержать только цифры и пробелы."));
    }

    @Step("Проверка ошибки валидации ФИО")
    public void isFIOValidationError() {
        this.message.shouldHave(text("ФИО должно содержать только русские буквы, пробелы и дефис."));
    }

    @Step("Вернуться к найденным рейсам")
    public void backToFlights() {
        this.backButton.click();
    }
}
//8й
