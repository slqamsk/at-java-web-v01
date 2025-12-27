package work.part08;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import work.part08.pages.InternetTestPage;
import work.part08.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотесты для the-internet.herokuapp.com с использованием Page Object Model
 * Самостоятельная работа - тестирование внешнего сайта
 * Сайт специально создан для тестирования, регистрация не требуется
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class InternetTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        // Окно будет максимизировано при первом open()
    }

    // 1. Проверка загрузки главной страницы
    @Test
    @DisplayName("1. Проверка загрузки главной страницы")
    void test01MainPageLoaded() {
        open("https://the-internet.herokuapp.com/");
        getWebDriver().manage().window().maximize();
        InternetTestPage page = new InternetTestPage();
        page.verifyPageLoaded();
        page.verifyPageHeading("Welcome to the-internet");
    }

    // 2. Успешный вход в систему
    @Test
    @DisplayName("2. Успешный вход в систему")
    void test02SuccessfulLogin() {
        open("https://the-internet.herokuapp.com/login");
        getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        loginPage.login("tomsmith", "SuperSecretPassword!");
        loginPage.verifySuccessfulLogin();
    }

    // 3. Неуспешный вход с неверными данными (негативный тест)
    @Test
    @DisplayName("3. Неуспешный вход с неверными данными")
    void test03FailedLogin() {
        open("https://the-internet.herokuapp.com/login");
        getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        loginPage.login("wrong_user", "wrong_password");
        loginPage.verifyLoginError();
    }

    // 4. Проверка страницы с формами
    @Test
    @DisplayName("4. Проверка страницы с формами")
    void test04FormsPage() {
        open("https://the-internet.herokuapp.com/login");
        getWebDriver().manage().window().maximize();
        InternetTestPage page = new InternetTestPage();
        page.verifyPageLoaded();
        page.verifyPageHeading("Login Page");
    }

    // 5. Проверка навигации по сайту
    @Test
    @DisplayName("5. Проверка навигации по сайту")
    void test05Navigation() {
        open("https://the-internet.herokuapp.com/");
        getWebDriver().manage().window().maximize();
        InternetTestPage page = new InternetTestPage();
        page.verifyPageLoaded();
        String currentUrl = getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("the-internet.herokuapp.com"),
            "URL должен содержать домен the-internet.herokuapp.com");
    }

    // 6. Проверка базовой функциональности
    @Test
    @DisplayName("6. Проверка базовой функциональности")
    void test06BasicFunctionality() {
        open("https://the-internet.herokuapp.com/");
        getWebDriver().manage().window().maximize();
        InternetTestPage page = new InternetTestPage();
        page.verifyPageLoaded();
        String pageSource = getWebDriver().getPageSource();
        Assertions.assertFalse(pageSource.isEmpty(),
            "Страница должна содержать контент");
    }
}

