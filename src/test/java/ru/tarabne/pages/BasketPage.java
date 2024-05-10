package ru.tarabne.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.tarabne.testdata.BasketTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasketPage {
    BasketTestData basketTestData = new BasketTestData();
    private final SelenideElement cartQuantity = $("#cart-quantity"),
            cartItems = $(".cart-items"),
            cartItemsHead = $(".cart-items__head"),
            emptyTheBasketButton = cartItemsHead.$(withText("Очистить корзину")),
            itemCardByNumber = cartItems.$$("article").get(0),
            deleteItemButton = itemCardByNumber.$(".wrap-item-price").$(".link-delete"),
            cleaningConfirmationModal = $("#modal-cart"),
            confirmCleaningButton = cleaningConfirmationModal.$(withText("Да")),
            cancelCleaningButton = cleaningConfirmationModal.$(withText("Нет")),
            itemId = itemCardByNumber.$(".sku");

    @Step("Перейти в корзину")
    public BasketPage openBasketPage() {
        open("/personal/cart2/");
        return this;
    }

    @Step("Нажать на кнопку удаления из корзины для первого товара")
    public BasketPage deleteFirstItem() {
        deleteItemButton.click();
        return this;
    }

    @Step("Нажать на кнопку \"Очистить корзину\"")
    public BasketPage cleanTheBasket() {
        emptyTheBasketButton.click();
        return this;
    }

    @Step("Подтвердить очистку корзины")
    public BasketPage confirmCleaningBasket() {
        confirmCleaningButton.click();
        return this;
    }

    @Step("Отменить очистку корзины")
    public BasketPage cancelCleaningBasket() {
        cancelCleaningButton.click();
        return this;
    }

    @Step("Проверить успешность удаления первого товара")
    public BasketPage firstItemDeletionCheck(String secondItemId) {
        itemId.shouldHave(text(secondItemId));
        return this;
    }

    @Step("Проверить успешность очистки корзины")
    public BasketPage basketCleaningCheck() {
        cartItems.shouldHave(text("Нет товаров в корзине"));
        return this;
    }

    @Step("Проверить успешность отмены очистки корзины")
    public BasketPage basketCleaningCancellationCheck() {
        cartItems.shouldNotHave(text("Нет товаров в корзине"));
        return this;
    }

    @Step("Проверить количество товаров в корзине")
    public BasketPage cartQuantityCheck(int quantity) {
        cartQuantity.shouldHave(text(String.valueOf(quantity)));
        return this;
    }
}
