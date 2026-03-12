package demo.part07;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FlightRegistrationTest {

    private static final String BASE_URL = "https://slqamsk.github.io/cases/slflights/v01/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "stand_pass1";

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = BASE_URL;
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        // Для Яндекс Браузера (если настроен):
        Configuration.browser = "firefox";
    }

    @Test
    void flightRegistrationFlow() {
        // 1. Логин
        open("/");
        $("#username").setValue(USERNAME);
        $("#password").setValue(PASSWORD);
        $("#loginButton").click();
        $("#message").shouldHave(text("Вход в систему выполнен успешно"), visible);
        $("#flightForm").shouldBe(visible);

        // 2. Поиск рейсов Москва - Нью-Йорк на ближайший понедельник
        $("#departureCity").selectOption("Москва");
        $("#arrivalCity").selectOption("Нью-Йорк");

        // Вычисляем ближайший понедельник
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
        if (nextMonday.isBefore(today)) {
            nextMonday = nextMonday.plusWeeks(1);
        }
        String mondayDate = nextMonday.format(DateTimeFormatter.ISO_LOCAL_DATE);
        $("#departureDate").setValue(mondayDate);

        //sleep(5000);

        $("#flightForm button").click();
        $("#flightsList").shouldBe(visible);

        // 3. Выбор первого рейса
        $("#flightsContainer tr:first-child .register-btn").click();
        $("#registrationForm").shouldBe(visible);

        // 4. Проверка данных рейса и пассажира
        $("#flightRegistrationInfo").shouldHave(
                text("Москва"),
                text("Нью-Йорк"),
                text(mondayDate)
        );

        $("#passengerName").shouldHave(value("Иванов Иван Иванович"));
        $("#passportNumber").shouldHave(value("1234 567890"));
        $("#email").shouldHave(value("ivanov@example.com"));
        $("#phone").shouldHave(value("+7 (123) 456-7890"));

        // 5. Завершение регистрации
        $("#registrationForm button").click();
        $("#registrationMessage").shouldHave(text("Регистрация успешно завершена!"), visible);

        // Проверка алерта с подтверждением (опционально, т.к. работа с alert в Selenide)
        //switchTo().alert().shouldHave(text("Бронирование завершено!")).accept();
    }
}
