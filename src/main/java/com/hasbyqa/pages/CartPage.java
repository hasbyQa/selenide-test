package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Cart Page Object
public class CartPage extends BasePage {

    // Locators
    private static final String PAGE_TITLE = ".title";
    private static final String CART_ITEMS = ".cart_item";
    private static final String CHECKOUT_BUTTON = "[data-test='checkout']";
    private static final String CONTINUE_SHOPPING_BUTTON = "[data-test='continue-shopping']";
    private static final String ITEM_PRICE = ".inventory_item_price";
    private static final String ITEM_QUANTITY = ".cart_quantity";

    @Step("Verify cart page is loaded")
    @Override
    public CartPage verifyPageLoaded() {
        $(PAGE_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Get cart items count")
    public int getCartItemsCount() {
        return getElementsCount(CART_ITEMS);
    }

    @Step("Get cart item price at index {index}")
    public String getCartItemPrice(int index) {
        return getText(".cart_item:nth-child(" + index + ") " + ITEM_PRICE);
    }

    @Step("Get cart item quantity at index {index}")
    public String getCartItemQuantity(int index) {
        return getText(".cart_item:nth-child(" + index + ") " + ITEM_QUANTITY);
    }

    @Step("Verify item is in cart")
    public boolean isItemInCart() {
        return getElementsCount(CART_ITEMS) > 0;
    }

    @Step("Click checkout button")
    public void clickCheckoutButton() {
        $(CHECKOUT_BUTTON).click();
    }

    @Step("Click continue shopping button")
    public void clickContinueShoppingButton() {
        $(CONTINUE_SHOPPING_BUTTON).click();
    }
}
