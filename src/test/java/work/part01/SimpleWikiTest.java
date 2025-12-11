package work.part01;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.DisplayName;

public class SimpleWikiTest {
    @Test
    void test01() {
        open("https://ru.wikipedia.org/wiki/Selenium");
        $("body").shouldHave(text("WebDriver"));
    }

    @Test
    void test02() {
        open("https://ru.wikipedia.org/wiki/Selenium");
        $("body").shouldHave(text("Selenide"));
        sleep(2000);
    }
}