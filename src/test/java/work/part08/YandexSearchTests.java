package work.part08;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import work.part08.pages.YandexMainPage;
import work.part08.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Автотесты для Яндекс.Поиска с использованием Page Object Model
 * Самостоятельная работа - тестирование внешнего сайта
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class YandexSearchTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://yandex.ru");
        getWebDriver().manage().window().maximize();
    }

    // 1. Проверка загрузки главной страницы
    @Test
    @DisplayName("1. Проверка загрузки главной страницы Яндекс")
    void test01MainPageLoaded() {
        YandexMainPage mainPage = new YandexMainPage();
        mainPage.verifyPageLoaded();
    }

    // 2. Успешный поиск по запросу
    @Test
    @DisplayName("2. Успешный поиск по запросу")
    void test02SuccessfulSearch() {
        YandexSearchPage searchPage = new YandexSearchPage();
        searchPage.enterSearchQuery("Selenium автоматизация тестирования");
        searchPage.performSearch();
        searchPage.verifySearchResultsPresent();
    }

    // 3. Поиск с помощью клавиши Enter
    @Test
    @DisplayName("3. Поиск с помощью клавиши Enter")
    void test03SearchWithEnter() {
        YandexSearchPage searchPage = new YandexSearchPage();
        searchPage.searchWithEnter("Java программирование");
        searchPage.verifySearchResultsPresent();
    }

    // 4. Поиск пустого запроса (негативный тест)
    @Test
    @DisplayName("4. Поиск пустого запроса")
    void test04EmptySearch() {
        YandexSearchPage searchPage = new YandexSearchPage();
        searchPage.enterSearchQuery("");
        searchPage.performSearch();
        // На главной странице должен остаться поиск
        searchPage.verifySearchResultsPresent();
    }

    // 5. Переход в Яндекс.Маркет
    @Test
    @DisplayName("5. Переход в Яндекс.Маркет")
    void test05GoToMarket() {
        YandexMainPage mainPage = new YandexMainPage();
        mainPage.goToMarket();
        // Проверяем, что перешли на страницу маркета
        getWebDriver().getCurrentUrl().contains("market.yandex.ru");
    }

    // 6. Поиск и проверка первого результата
    @Test
    @DisplayName("6. Поиск и проверка первого результата")
    void test06SearchAndCheckFirstResult() {
        YandexSearchPage searchPage = new YandexSearchPage();
        searchPage.enterSearchQuery("автоматизация тестирования");
        searchPage.performSearch();
        searchPage.verifySearchResultsPresent();
        // Первый результат должен содержать ключевые слова
        searchPage.verifyFirstResultContains("автоматизация");
    }
}

