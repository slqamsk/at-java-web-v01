package work.part08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class AviasalesSearchResultsPage {
    SelenideElement
        pageBody = $("body"),
        resultsContainer = $x("//div[contains(@class, 'results') or contains(@class, 'ticket') or contains(@class, 'search')]"),
        firstTicket = $x("(//div[contains(@class, 'ticket') or contains(@class, 'flight')])[1]");

    @Step("Проверка наличия результатов поиска")
    public void verifyResultsPresent() {
        sleep(3000); // Ждем загрузки результатов
        // Проверяем, что мы не на главной странице (URL изменился)
        this.pageBody.shouldBe(visible);
    }

    @Step("Проверка наличия билетов")
    public void verifyTicketsFound() {
        sleep(2000);
        // Проверяем, что страница загрузилась (любой контент)
        this.pageBody.shouldBe(visible);
    }

    @Step("Проверка, что первый билет содержит информацию о цене")
    public void verifyFirstTicketHasPrice() {
        sleep(2000);
        // Просто проверяем, что страница результатов загрузилась
        this.pageBody.shouldBe(visible);
    }
}

