package work.part0224;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Тесты нажатий виджета погоды на ya.ru — уточнение прогноза на ближайшую неделю.
 * Главная: https://ya.ru/ → клик по погоде → прогноз на 10 дней.
 */
public class YaRuWeatherTest {

    private static final String YA_RU = "https://ya.ru/";
    private static final String POGODA_BASE = "https://yandex.ru/pogoda/";

    @BeforeEach
    void setUp() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
    }

    @Test
    void weatherWidgetIsVisibleOnMainPage() {
        open(YA_RU);

        // Виджет погоды на главной (ссылка на pogoda)
        SelenideElement weatherLink = $("a[href*='pogoda']").shouldBe(visible);
        weatherLink.shouldHave(attribute("href"));
    }

    @Test
    void clickWeatherOpensPogodaPage() {
        open(YA_RU);

        SelenideElement weatherLink = $("a[href*='pogoda']").shouldBe(visible);
        weatherLink.click();

        sleep(2000);
        // Должны оказаться на странице погоды
        $("body").shouldHave(text("Погода").or(text("погода")).or(text("Прогноз")));
    }

    @Test
    void weeklyForecastTabIsVisibleAndClickable() {
        // Прямой переход на страницу погоды (Москва по умолчанию)
        open(POGODA_BASE);

        sleep(2000);

        // Вкладка "10 дней" — прогноз на ближайшую неделю
        SelenideElement tenDaysTab = $x("//a[contains(.,'10 дней') or contains(@href,'10-days')]");
        tenDaysTab.shouldBe(visible);

        tenDaysTab.click();
        sleep(2000);

        // Проверяем, что появился контент недельного прогноза (дни, температура)
        $("body").shouldHave(text("°").or(text("º")).or(text("Пн").or(text("Вт")).or(text("Ср"))));
    }

    @Test
    void fullFlowFromYaRuToWeeklyForecast() {
        open(YA_RU);

        // Клик по виджету погоды
        SelenideElement weatherLink = $("a[href*='pogoda']").shouldBe(visible);
        weatherLink.click();

        sleep(3000);

        // Ищем вкладку "10 дней" на странице погоды
        SelenideElement tenDaysTab = $x("//a[contains(.,'10 дней') or contains(@href,'10-days')]");
        tenDaysTab.shouldBe(visible).click();

        sleep(2000);

        // Уточнённый прогноз на неделю должен отображаться
        $("body").shouldHave(text("Пн").or(text("Вт")).or(text("Прогноз")).or(text("°")));
    }
}
