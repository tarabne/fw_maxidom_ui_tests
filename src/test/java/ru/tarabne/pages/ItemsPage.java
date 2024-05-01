package ru.tarabne.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.tarabne.pages.components.NotificationWidgetComponent;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ItemsPage {
    NotificationWidgetComponent notificationWidgetComponent = new NotificationWidgetComponent();
    private final SelenideElement breadcrumbs = $(".breadcrumbs"),
        contentHeader = $(".content-pages_title"),
        contentItems = $(".b-catalog-items"),
        itemByIndex = contentItems.$$("article").get(1),
        itemHeader = itemByIndex.$("span[itemprop='name']"),
        itemsNotFoundText = $("main.main");

    @Step("Проверить наличие заголовка \"Результаты поиска\"")
    public ItemsPage checkItemPageHeader() {
        contentHeader.shouldHave(text("Результаты поиска"));
        return this;
    }

    @Step("Проверить, что элемент \"хлебные крошки\" содержит \"Результат поиска по запросу \"{value}\"\"")
    public ItemsPage checkBreadcrumbs(String value) {
        breadcrumbs.shouldHave(text("Результат поиска по запросу \"" + value + "\""));
        return this;
    }

    @Step("Проверить, что второй найденный товар в названии содержит слово \"{value}\"")
    public ItemsPage checkItemNameByItemNumber(String value) {
        notificationWidgetComponent.closeNotificationWidget();
        itemHeader.shouldHave(text(value));
        return this;
    }

    @Step("Проверить отсутствие результатов поиска")
    public ItemsPage checkEmptyItemsList() {
        contentItems.shouldNotBe(exist);
        return this;
    }

    @Step("Проверить отображение текста об отсутствии результатов поиска")
    public ItemsPage checkItemsNotFoundText() {
        itemsNotFoundText.shouldHave(text("Пока ничего не найдено :( Введите слово для поиска."));
        return this;
    }

    public ItemsPage closeWidget() {
        notificationWidgetComponent.closeNotificationWidget();
        return this;
    }


}
