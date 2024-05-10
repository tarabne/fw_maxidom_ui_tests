package ru.tarabne.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tarabne.api.Precondition;
import ru.tarabne.pages.BasketPage;
import ru.tarabne.testdata.BasketTestData;

@Story("Корзина товаров")
@Feature("UI | Список товаров в корзине")
@Issue("MXD-366")
@DisplayName("Тесты на корзину товаров")
public class BasketTests extends BaseTest {
    BasketPage basketPage = new BasketPage();
    Precondition precondition = new Precondition();
    BasketTestData basketTestData = new BasketTestData();

    @DisplayName("Предзаполнение корзины товарами")
    @BeforeEach
    void basketPrecondition() {
        precondition.populateCart();
    }

    @Test
    @DisplayName("Удаление одного товара из корзины")
    @Owner("tarabne")
    @Severity(SeverityLevel.NORMAL)
    void oneItemShouldBeDeletedTest() {
        basketPage.openBasketPage()
                .deleteFirstItem();

        basketPage.cartQuantityCheck(1)
                .firstItemDeletionCheck(basketTestData.secondItemInBasketId);
    }


    @Test
    @DisplayName("Очистка корзины")
    @Owner("tarabne")
    @Severity(SeverityLevel.NORMAL)
    void allItemsShouldBeDeletedTest() {
        basketPage.openBasketPage()
                .cleanTheBasket()
                .confirmCleaningBasket();

        basketPage.cartQuantityCheck(0)
                .basketCleaningCheck();
    }

    @Test
    @DisplayName("Отмена очистки корзины")
    @Owner("tarabne")
    @Severity(SeverityLevel.MINOR)
    void itemsShouldNotBeDeletedTest() {
        basketPage.openBasketPage()
                .cleanTheBasket()
                .cancelCleaningBasket();

        basketPage.cartQuantityCheck(2)
                .basketCleaningCancellationCheck();
    }
}
