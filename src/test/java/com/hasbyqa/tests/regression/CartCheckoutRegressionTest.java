package com.hasbyqa.tests.regression;

import com.hasbyqa.pages.*;
import com.hasbyqa.tests.base.BaseTest;
import com.hasbyqa.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Regression Tests")
@Feature("Cart and Checkout")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartCheckoutRegressionTest extends BaseTest {

    private void loginUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);
    }

    @Test
    @Order(1)
    @DisplayName("Verify cart contains correct item after adding product")
    @Story("Cart functionality")
    @Description("Cart should display the product that was added to it")
    void testProductInCart() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addFirstProductToCart();
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();

        assertTrue(cartPage.isItemInCart(), "Product should be in cart");
        assertEquals(1, cartPage.getCartItemsCount(), "Cart should contain exactly 1 item");
    }

    @Test
    @Order(2)
    @DisplayName("Verify multiple products can be added to cart")
    @Story("Cart functionality")
    @Description("User should be able to add multiple products to cart")
    void testMultipleProductsInCart() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        productsPage.addProductToCartByIndex(1);
        productsPage.addProductToCartByIndex(2);
        productsPage.addProductToCartByIndex(3);

        assertEquals(3, productsPage.getCartItemsCount(), "Cart should contain 3 items");
    }

    @Test
    @Order(3)
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
        cartPage.clickContinueShoppingButton();

        productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        assertTrue(productsPage.getProductCount() > 0, "Should be back on products page");
    }

    @Test
    @Order(4)
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
        checkoutStepOne.clickContinueButton();

        assertTrue(checkoutStepOne.isElementVisible("[data-test='error']"),
                "Error message should be displayed for required field");
    }

    @Test
    @Order(5)
    @DisplayName("Verify successful checkout with valid information")
    @Story("Checkout process")
    @Description("User should be able to complete checkout with valid personal information")
    void testSuccessfulCheckout() {
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
        checkoutStepOne.fillCheckoutInfo(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );
        checkoutStepOne.clickContinueButton();

        CheckoutStepTwoPage checkoutStepTwo = new CheckoutStepTwoPage();
        checkoutStepTwo.verifyPageLoaded();

        assertNotNull(checkoutStepTwo.getItemTotal(), "Item total should be displayed");
        assertNotNull(checkoutStepTwo.getTaxAmount(), "Tax should be displayed");
        assertNotNull(checkoutStepTwo.getTotalAmount(), "Total should be displayed");

        checkoutStepTwo.clickFinishButton();

        CheckoutCompletePage completePage = new CheckoutCompletePage();
        completePage.verifyPageLoaded();
        assertTrue(completePage.isOrderCompleted(), "Order should be completed successfully");
    }

    @Test
    @Order(6)
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
        assertTrue(completePage.isOrderCompleted(), "Order should complete with alternative data");
    }

    @Test
    @Order(7)
    @DisplayName("[BUG-001] Checkout accepts empty cart - missing validation")
    @Story("Checkout validation")
    @Description(
            "BUG REPORT - BUG-001\n\n" +
                    "Title: Checkout button should be disabled or show error when cart is empty\n\n" +
                    "Severity: High | Priority: High\n" +
                    "Environment: Swag Labs - https://www.saucedemo.com\n" +
                    "Browser: Chrome (headless)\n" +
                    "Reporter: hasbyQa\n\n" +
                    "EXPECTED BEHAVIOUR:\n" +
                    "When a user navigates to the cart page with no items and clicks Checkout, " +
                    "the application should either disable the Checkout button or display a " +
                    "clear validation error message such as 'Your cart is empty'.\n\n" +
                    "ACTUAL BEHAVIOUR:\n" +
                    "The application allows the user to proceed to the checkout information " +
                    "form even when the cart is completely empty, with no warning or error shown.\n\n" +
                    "STEPS TO REPRODUCE:\n" +
                    "1. Navigate to https://www.saucedemo.com\n" +
                    "2. Login with username: standard_user / password: secret_sauce\n" +
                    "3. Do NOT add any products to the cart\n" +
                    "4. Click the shopping cart icon in the top right\n" +
                    "5. Verify cart is empty (no items listed)\n" +
                    "6. Click the Checkout button\n" +
                    "7. Observe that the application proceeds to checkout step one form\n" +
                    "8. Assert that an error message is shown - THIS ASSERTION FAILS\n\n" +
                    "ROOT CAUSE ANALYSIS:\n" +
                    "The cart page has no client-side validation before allowing checkout. " +
                    "The Checkout button is always enabled regardless of cart state.\n\n" +
                    "WORKAROUND:\n" +
                    "None - users must manually ensure they have items before checking out."
    )
    void testBugEmptyCartCheckoutValidation() {
        loginUser();

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();

        // Go to cart without adding any products
        productsPage.clickCartLink();

        CartPage cartPage = new CartPage();
        cartPage.verifyPageLoaded();

        // Verify cart is empty
        assertEquals(0, cartPage.getCartItemsCount(),
                "Cart should be empty - no products were added");

        // Click checkout on empty cart
        cartPage.clickCheckoutButton();

        // BUG-001: Application proceeds to checkout instead of showing an error
        // This assertion intentionally fails to demonstrate the bug
        assertTrue(
                cartPage.isElementVisible("[data-test='error']"),
                "BUG-001: Expected validation error when checking out with empty cart. " +
                        "Actual: Application proceeded to checkout step one without any warning."
        );
    }
}