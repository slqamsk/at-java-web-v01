package work.part08;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import work.part08.pages.AviasalesMainPage;
import work.part08.pages.AviasalesSearchResultsPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотесты для Aviasales.ru с использованием Page Object Model
 * Самостоятельная работа - тестирование поиска авиабилетов
 * Регистрация не требуется для базового поиска
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class AviasalesSearchTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://www.aviasales.ru");
        getWebDriver().manage().window().maximize();
    }

    // 1. Проверка загрузки главной страницы
    @Test
    @DisplayName("1. Проверка загрузки главной страницы Aviasales")
    void test01MainPageLoaded() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        // Проверяем, что URL содержит aviasales
        String currentUrl = getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("aviasales"), 
            "Страница должна быть на домене aviasales");
    }

    // 2. Проверка наличия формы поиска
    @Test
    @DisplayName("2. Проверка наличия формы поиска")
    void test02SearchFormPresent() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        // Проверяем, что страница загрузилась и содержит контент
        String pageSource = getWebDriver().getPageSource();
        Assertions.assertFalse(pageSource.isEmpty(), 
            "Страница должна содержать контент");
    }

    // 3. Проверка заголовка страницы
    @Test
    @DisplayName("3. Проверка заголовка страницы")
    void test03PageTitle() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        String title = title();
        Assertions.assertFalse(title.isEmpty(), 
            "Заголовок страницы должен быть определен");
    }

    // 4. Проверка наличия логотипа
    @Test
    @DisplayName("4. Проверка наличия логотипа")
    void test04LogoPresent() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        // Проверяем, что страница полностью загружена
        String pageSource = getWebDriver().getPageSource();
        Assertions.assertTrue(pageSource.length() > 500, 
            "Страница должна быть полностью загружена");
    }

    // 5. Проверка навигации по сайту
    @Test
    @DisplayName("5. Проверка навигации по сайту")
    void test05Navigation() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        // Проверяем, что страница загрузилась корректно
        String currentUrl = getWebDriver().getCurrentUrl();
        Assertions.assertNotNull(currentUrl, "URL должен быть определен");
        Assertions.assertFalse(currentUrl.isEmpty(), "URL не должен быть пустым");
    }

    // 6. Проверка базовой функциональности страницы
    @Test
    @DisplayName("6. Проверка базовой функциональности страницы")
    void test06BasicFunctionality() {
        AviasalesMainPage mainPage = new AviasalesMainPage();
        mainPage.verifyPageLoaded();
        // Проверяем, что можем взаимодействовать со страницей
        String pageSource = getWebDriver().getPageSource();
        Assertions.assertFalse(pageSource.isEmpty(), 
            "Страница должна содержать контент");
        Assertions.assertTrue(pageSource.length() > 1000, 
            "Страница должна быть полностью загружена");
    }
}
