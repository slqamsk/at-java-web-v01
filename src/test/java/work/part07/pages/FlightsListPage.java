package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsListPage {
    SelenideElement
            flightsTable = $("#flightsTable"),
            registerButton = $x("//button[.='Зарегистрироваться']"),
            newSearchButton = $x("//button[.='Новый поиск']"),
            sortByTimeButton = $x("//button[contains(.,'Время')]"),
            sortByPriceButton = $x("//button[contains(.,'Цена')]");

    @Step("Выбираем первый рейс в списке")
    public void registerToFirstFlight() {
        this.registerButton.click();
    }

    @Step("Проверяем, что рейсов не найдено")
    public void isNoFlights() {
        flightsTable.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }

    @Step("Проверяем, что рейсы найдены")
    public void areFlightsFound() {
        flightsTable.shouldBe(visible);
        registerButton.shouldBe(visible);
    }

    @Step("Сортировка по времени")
    public void sortByTime() {
        this.sortByTimeButton.click();
    }

    @Step("Сортировка по цене")
    public void sortByPrice() {
        this.sortByPriceButton.click();
    }

    @Step("Новый поиск")
    public void newSearch() {
        this.newSearchButton.click();
    }
}