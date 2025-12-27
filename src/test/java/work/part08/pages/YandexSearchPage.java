package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class YandexSearchPage {
    SelenideElement
        searchInput = $("#text"),
        searchButton = $x("//button[@type='submit']"),
        searchResults = $("#search-result"),
        firstResult = $x("//li[@data-cid][1]//h2//a");

    @Step("Ввод поискового запроса")
    public void enterSearchQuery(String query) {
        this.searchInput.setValue(query);
    }

    @Step("Выполнение поиска")
    public void performSearch() {
        this.searchButton.click();
    }

    @Step("Поиск с помощью Enter")
    public void searchWithEnter(String query) {
        this.searchInput.setValue(query).pressEnter();
    }

    @Step("Проверка наличия результатов поиска")
    public void verifySearchResultsPresent() {
        this.searchResults.shouldBe(visible);
    }

    @Step("Проверка текста в первом результате")
    public void verifyFirstResultContains(String expectedText) {
        this.firstResult.shouldHave(text(expectedText));
    }

    @Step("Клик по первому результату")
    public void clickFirstResult() {
        this.firstResult.click();
    }
}

