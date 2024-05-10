package ru.tarabne.pages;

import io.qameta.allure.Step;
import ru.tarabne.pages.components.SearchBoxComponent;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    SearchBoxComponent searchBoxComponent = new SearchBoxComponent();

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("");
        return this;
    }

    @Step("Выполнить поиск по запросу \"{searchQuery}\"")
    public MainPage doSearch(String searchQuery) {
        searchBoxComponent.searchProduct(searchQuery);
        return this;
    }
}
