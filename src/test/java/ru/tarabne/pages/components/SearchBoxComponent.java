package ru.tarabne.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchBoxComponent {
    private final SelenideElement searchQueryField = $(".header__2nd-row").$("#q"),
        searchButton = $(".header__2nd-row").$("button");
    public void productSearch(String searchText) {
        searchQueryField.setValue(searchText);
        searchButton.click();
    }
}
