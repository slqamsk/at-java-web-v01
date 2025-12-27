package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InternetTestPage {
    SelenideElement
        pageHeading = $("h1, h2, h3"),
        pageBody = $("body");

    @Step("Проверка загрузки страницы")
    public void verifyPageLoaded() {
        this.pageBody.shouldBe(visible);
    }

    @Step("Проверка заголовка страницы")
    public void verifyPageHeading(String expectedHeading) {
        this.pageHeading.shouldHave(text(expectedHeading));
    }
}

