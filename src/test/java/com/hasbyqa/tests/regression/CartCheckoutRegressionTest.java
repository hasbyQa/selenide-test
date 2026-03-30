package com.hasbyqa.tests.regression;

import com.hasbyqa.pages.*;
import com.hasbyqa.tests.base.BaseTest;
import com.hasbyqa.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Regression tests for cart and checkout functionality
@Epic("Regression Tests")
@Feature("Cart and Checkout")
public class CartCheckoutRegressionTest extends BaseTest {

    // Helper method to login before each test
    private void loginUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);
    }

    @Test
    @DisplayName("Verify cart contains correct item after adding product")
    @Story("Cart functionality")
    @Description("Cart should display the product that was added to it")
    void testProductInCart() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addFirstProductToCart();

        // Navigate to cart
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();

        // Assert: Item should be in cart
        assertTrue(cartPage.isItemInCart(), "Product should be in cart");
        assertEquals(1, cartPage.getCartItemsCount(), "Cart should contain exactly 1 item");
    }

    @Test
    @DisplayName("Verify multiple products can be added to cart")
    @Story("Cart functionality")
    @Description("User should be able to add multiple products to cart")
    void testMultipleProductsInCart() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();

        // Test: Add multiple products
        productsPage.addProductToCartByIndex(1);
        productsPage.addProductToCartByIndex(2);
        productsPage.addProductToCartByIndex(3);

        // Assert: Cart should show 3 items
        assertEquals(3, productsPage.getCartItemsCount(), "Cart should contain 3 items");
    }

    @Test
    @DisplayName("Verify successful checkout with valid information")
    @Story("Checkout process")
    @Description("User should be able to complete checkout with valid personal information")
    void testSuccessfulCheckout() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addFirstProductToCart();

        // Navigate to cart
        productsPage.clickCartLink();
        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();

        // Test: Click checkout
        cartPage.clickCheckoutButton();

        // Fill checkout info
        CheckoutStepOnePage checkoutStepOne = new CheckoutStepOnePage();
        checkoutStepOne.verifyPageLoaded();
        checkoutStepOne.fillCheckoutInfo(
            TestData.FIRST_NAME,
            TestData.LAST_NAME,
            TestData.POSTAL_CODE
        );
        checkoutStepOne.clickContinueButton();

        // Verify checkout step two
        CheckoutStepTwoPage checkoutStepTwo = new CheckoutStepTwoPage();
        checkoutStepTwo.verifyPageLoaded();

        // Assert: Order summary should be visible
        assertNotNull(checkoutStepTwo.getItemTotal(), "Item total should be displayed");
        assertNotNull(checkoutStepTwo.getTaxAmount(), "Tax should be displayed");
        assertNotNull(checkoutStepTwo.getTotalAmount(), "Total should be displayed");

        // Test: Complete order
        checkoutStepTwo.clickFinishButton();

        // Assert: Order completion
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        completePage.verifyPageLoaded();
        assertTrue(completePage.isOrderCompleted(), "Order should be completed successfully");
    }

    @Test
    @DisplayName("Verify checkout step one validates required fields")
    @Story("Checkout validation")
    @Description("Checkout should fail if required fields are empty")
    void testCheckoutValidation() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addFirstProductToCart();
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();
        cartPage.clickCheckoutButton();

        CheckoutStepOnePage checkoutStepOne = new CheckoutStepOnePage();
        checkoutStepOne.verifyPageLoaded();

        // Test: Click continue without filling form
        checkoutStepOne.clickContinueButton();

        // Assert: Error should be shown
        assertTrue(checkoutStepOne.isElementVisible("[data-test='error']"), 
            "Error message should be displayed for required field");
    }

    @Test
    @DisplayName("Verify cart continue shopping button returns to products")
    @Story("Cart navigation")
    @Description("User should be able to return to products page from cart using continue shopping button")
    void testContinueShoppingFromCart() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addFirstProductToCart();
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();

        // Test: Click continue shopping
        cartPage.clickContinueShoppingButton();

        // Assert: Should be back on products page
        productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        assertTrue(productsPage.getProductCount() > 0, "Should be back on products page");
    }

    @Test
    @DisplayName("Verify checkout with alternative user data")
    @Story("Checkout process")
    @Description("Checkout should work with different user information")
    void testCheckoutWithAlternativeData() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addProductToCartByIndex(1);
        productsPage.addProductToCartByIndex(2);
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();
        cartPage.clickCheckoutButton();

        CheckoutStepOnePage checkoutStepOne = new CheckoutStepOnePage();
        checkoutStepOne.verifyPageLoaded();
        checkoutStepOne.fillCheckoutInfo(
            TestData.ALT_FIRST_NAME,
            TestData.ALT_LAST_NAME,
            TestData.ALT_POSTAL_CODE
        );
        checkoutStepOne.clickContinueButton();

        CheckoutStepTwoPage checkoutStepTwo = new CheckoutStepTwoPage();
        checkoutStepTwo.verifyPageLoaded();
        checkoutStepTwo.clickFinishButton();

        CheckoutCompletePage completePage = new CheckoutCompletePage();
        completePage.verifyPageLoaded();

        // Assert: Order completed successfully
        assertTrue(completePage.isOrderCompleted(), "Order should complete with alternative data");
    }
}
