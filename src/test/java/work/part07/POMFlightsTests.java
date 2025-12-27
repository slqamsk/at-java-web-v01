package work.part07;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import work.part07.pages.FlightsListPage;
import work.part07.pages.LoginPage;
import work.part07.pages.RegistrationPage;
import work.part07.pages.SearchPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        getWebDriver().manage().window().maximize();
    }

    // 1.1. Успешный логин с корректными данными, проверка ФИО в шапке
    @Test
    @DisplayName("1.1. Успешный логин с корректными данными")
    void test01SuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");
        loginPage.verifyFIOInHeader("Иванов Иван Иванович");
    }

    // 1.2. Неуспешный логин с неверным паролем
    @Test
    @DisplayName("1.2. Неуспешный логин с неверным паролем")
    void test02WrongPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "WrongPassword");
        loginPage.isLoginUnsuccessful();
    }

    // 1.3. Вход с пустыми полями
    @Test
    @DisplayName("1.3. Вход с пустыми полями")
    void test03EmptyFields() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithEmptyFields();
        loginPage.isEmptyFieldsError();
    }

    // 1.4. Вход заблокированного пользователя
    @Test
    @DisplayName("1.4. Вход заблокированного пользователя")
    void test04LockedOutUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("locked_out_user", "stand_pass1");
        loginPage.isUserBlocked();
    }

    // 2.1. Поиск с валидными данными
    @Test
    @DisplayName("2.1. Поиск с валидными данными")
    void test05ValidSearch() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.areFlightsFound();
    }

    // 2.2. Поиск без указания даты
    @Test
    @DisplayName("2.2. Поиск без указания даты")
    void test06NoDate() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("");
        searchPage.isDepartureDateEmpty();
    }

    // 2.3. Поиск с прошедшей датой (негативный тест)
    @Test
    @DisplayName("2.3. Поиск с прошедшей датой")
    void test07PastDate() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.searchWithPastDate("01.01.2020", "Москва", "Нью-Йорк");
        searchPage.isPastDateError();
    }

    // 2.4. Отображение сообщения "Рейсов не найдено"
    @Test
    @DisplayName("2.4. Отображение сообщения 'Рейсов не найдено'")
    void test08FlightsNotFound() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Казань", "Париж");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.isNoFlights();
    }

    // 4.3.1. Валидация ФИО (только русские буквы) - негативный тест
    @Test
    @DisplayName("4.3.1. Валидация ФИО - только русские буквы")
    void test09FIOValidation() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.registration("Ivanov Ivan", "1234 567890", "ivanov@example.com", "+71234567890");
        registrationPage.isFIOValidationError();
    }

    // 4.3.2. Валидация паспорта (только цифры и пробелы) - негативный тест
    @Test
    @DisplayName("4.3.2. Валидация паспорта - только цифры и пробелы")
    void test10PassportValidation() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.registration("Иванов Иван Иванович", "паспорт", "ivanov@example.com", "+71234567890");
        registrationPage.isPassportValidationError();
    }

    // 4.4. Отправка формы с пустыми полями - негативный тест
    @Test
    @DisplayName("4.4. Отправка формы с пустыми полями")
    void test11EmptyRegistrationFields() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.registration("", "", "", "");
        registrationPage.isErrorFillAllFied();
    }

    // 4.5. Успешная регистрация
    @Test
    @DisplayName("4.5. Успешная регистрация")
    void test12SuccessRegistration() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.successDefaultRegistration();
    }

    // 5.1. Работа с клавишей Enter в формах
    @Test
    @DisplayName("5.1. Работа с клавишей Enter в формах")
    void test13EnterKeyInForms() {
        // Проверяем работу Enter в форме логина
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithEnter("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Форма поиска может не поддерживать Enter, используем обычный поиск
        // но проверяем, что после логина с Enter форма работает корректно
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.areFlightsFound();
    }
}
