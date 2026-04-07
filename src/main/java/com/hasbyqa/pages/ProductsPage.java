package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;

public class ProductsPage extends BasePage {

    private static final String PAGE_TITLE = ".title";
    private static final String PRODUCTS_LIST = ".inventory_list";
    private static final String PRODUCT_ITEM = ".inventory_item";
    private static final String PRODUCT_NAME = ".inventory_item_name";
    private static final String PRODUCT_PRICE = ".inventory_item_price";
    private static final String ADD_TO_CART_BUTTON = "[data-test*='add-to-cart']";
    private static final String CART_LINK = ".shopping_cart_link";
    private static final String CART_BADGE = ".shopping_cart_badge";
    private static final String BURGER_MENU = "#react-burger-menu-btn";
    private static final String LOGOUT_LINK = "#logout_sidebar_link";

    @Step("Verify products page is loaded")
    @Override
    public ProductsPage verifyPageLoaded() {
        webdriver().shouldHave(urlContaining("inventory"));
        $(PAGE_TITLE).shouldBe(visible);
        $(PRODUCTS_LIST).shouldBe(visible);
        return this;
    }

    @Step("Get product count")
    public int getProductCount() {
        return getElementsCount(PRODUCT_ITEM);
    }

    @Step("Get first product name")
    public String getFirstProductName() {
        return $$(PRODUCT_ITEM).first().$(".inventory_item_name").getText();
    }

    @Step("Get product price by index {index}")
    public String getProductPriceByIndex(int index) {
        return $$(PRODUCT_ITEM).get(index - 1).$(PRODUCT_PRICE).getText();
    }

    @Step("Click add to cart button for first product")
    public ProductsPage addFirstProductToCart() {
        $$(ADD_TO_CART_BUTTON).first().shouldBe(visible).click();
        return this;
    }

    @Step("Click add to cart button for product at index {index}")
    public ProductsPage addProductToCartByIndex(int index) {
        $$(PRODUCT_ITEM).get(index - 1).$(ADD_TO_CART_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step("Get cart items count")
    public int getCartItemsCount() {
        try {
            return Integer.parseInt($(CART_BADGE).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Click on cart link")
    public void clickCartLink() {
        $(CART_LINK).shouldBe(visible).click();
        // Wait for navigation to cart page to complete before returning
        webdriver().shouldHave(urlContaining("cart"));
    }

    @Step("Click on product name {productName}")
    public void clickProductByName(String productName) {
        $$(PRODUCT_NAME).findBy(com.codeborne.selenide.Condition.text(productName)).click();
    }

    @Step("Logout from the application")
    public void logout() {
        $(BURGER_MENU).click();
        $(LOGOUT_LINK).click();
    }
}