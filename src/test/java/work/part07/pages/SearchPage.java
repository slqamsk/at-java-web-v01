package work.part07.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    SelenideElement
            cityFrom = $("#departureCity"),
            cityTo = $("#arrivalCity"),
            departureDate = $("#departureDate"),
            findButton = $x("//button[.='Найти']"),
            message = $("#searchMessage");

    @Step("Поиск рейсов (задаём только дату)")
    public void search(String departureDate) {
        this.departureDate.setValue(departureDate);
        this.findButton.click();
    }

    @Step("Поиск рейсов")
    public void search(String departureDate, String from, String to) {
        this.departureDate.setValue(departureDate);
        this.cityFrom.selectOption(from);
        this.cityTo.selectOption(to);
        this.findButton.click();
    }

    @Step("Проверка, что дата не указана")
    public void isDepartureDateEmpty() {
        this.message.shouldHave(text("Пожалуйста, укажите дату вылета."));
    }
}

    private String makeDateCorrect(String date) {
        if (Configuration.browser == "firefox" && date.length() == 10)
            String[] parts = date.split("-");
        return date.substring(6,10) + "-" +
                date.substring(3,5) + "-"
                +
                date.substring(0,2);
    }
    return date;
}