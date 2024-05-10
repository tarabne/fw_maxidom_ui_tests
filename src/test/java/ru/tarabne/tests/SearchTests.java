package ru.tarabne.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tarabne.pages.ItemsPage;
import ru.tarabne.pages.MainPage;
import ru.tarabne.testdata.SearchTestData;

@Story("Поиск товаров")
@Feature("UI | Поиск товаров")
@Issue("MXD-452")
@DisplayName("Тесты на поиск товаров")
public class SearchTests extends BaseTest {
    MainPage mainPage = new MainPage();
    ItemsPage itemsPage = new ItemsPage();
    SearchTestData searchTestData = new SearchTestData();

    @Test
    @DisplayName("Поиск товаров с поисковым запросом с результатами")
    @Owner("tarabne")
    @Severity(SeverityLevel.CRITICAL)
    void successfulSearchTest() {
        mainPage.openPage()
                .doSearch(searchTestData.searchQueryWithResults);

        itemsPage.closeWidget()
                .checkItemPageHeader()
                .checkBreadcrumbs(searchTestData.searchQueryWithResults)
                .checkSecondItemName(searchTestData.searchQueryWithResults);
    }

    @Test
    @DisplayName("Поиск товаров с поисковым запросом без результатов")
    @Owner("tarabne")
    @Severity(SeverityLevel.NORMAL)
    void nonExistantItemSearchTest() {
        mainPage.openPage()
                .doSearch(searchTestData.searchQueryWithoutResults);

        itemsPage.closeWidget()
                .checkItemPageHeader()
                .checkBreadcrumbs(searchTestData.searchQueryWithoutResults)
                .checkEmptyItemsList();
    }

    @Test
    @DisplayName("Поиск товаров без поискового запроса")
    @Owner("tarabne")
    @Severity(SeverityLevel.MINOR)
    void emptyStringSearchTest() {
        mainPage.openPage()
                .doSearch(searchTestData.emptySearchQuery);

        itemsPage.closeWidget()
                .checkItemPageHeader()
                .checkEmptyItemsList()
                .checkItemsNotFoundText();
    }

}

