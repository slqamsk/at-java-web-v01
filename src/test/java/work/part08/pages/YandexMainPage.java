package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class YandexMainPage {
    SelenideElement
        logo = $(".home-logo"),
        searchInput = $("#text"),
        marketLink = $x("//a[@data-id='market']"),
        imagesLink = $x("//a[@data-id='images']"),
        videoLink = $x("//a[@data-id='video']");

    @Step("Проверка загрузки главной страницы")
    public void verifyPageLoaded() {
        this.logo.shouldBe(visible);
        this.searchInput.shouldBe(visible);
    }

    @Step("Переход в Яндекс.Маркет")
    public void goToMarket() {
        this.marketLink.click();
    }

    @Step("Переход в Яндекс.Картинки")
    public void goToImages() {
        this.imagesLink.click();
    }

    @Step("Переход в Яндекс.Видео")
    public void goToVideo() {
        this.videoLink.click();
    }
}

