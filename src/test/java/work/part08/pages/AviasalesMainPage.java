package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AviasalesMainPage {
    SelenideElement
        pageBody = $("body"),
        pageTitle = $("title");

    @Step("Проверка загрузки главной страницы")
    public void verifyPageLoaded() {
        // Проверяем, что страница загрузилась
        this.pageBody.shouldBe(visible);
        sleep(3000); // Даем время на полную загрузку страницы
    }
}
