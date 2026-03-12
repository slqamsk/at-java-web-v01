package demo.part08;

import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class YandexTests {
    @Test
    void test01YandexBrowser() {
        // Путь к бинарнику Яндекс.Браузера
        String yandexPath = System.getenv("LOCALAPPDATA") +
                "\\Yandex\\YandexBrowser\\Application\\browser.exe";
        // Номер версии - в Яндекс браузере на странице chrome://version
//        WebDriverManager.chromedriver().browserVersion("142").setup();
        Configuration.browserCapabilities = new ChromeOptions().setBinary(yandexPath);
        open("https://slqa.ru");
    }
}