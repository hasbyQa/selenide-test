package com.hasbyqa.tests.smoke;

import com.hasbyqa.pages.LoginPage;
import com.hasbyqa.pages.ProductsPage;
import com.hasbyqa.tests.base.BaseTest;
import com.hasbyqa.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Smoke tests for critical functionality - Login and Product Page
@Epic("Smoke Tests")
@Feature("Login and Products")
public class LoginSmokeTest extends BaseTest {

    @Test
    @DisplayName("Verify successful login with valid credentials")
    @Story("Login functionality")
    @Description("User should be able to login with valid credentials and see products page")
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();

        // Test: Login
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        // Assert: Products page should be loaded
        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();
        assertTrue(productsPage.getProductCount() > 0, "Products should be displayed");
    }

    @Test
    @DisplayName("Verify login fails with invalid credentials")
    @Story("Login validation")
    @Description("Login should fail with invalid credentials and show error message")
    void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();

        // Test: Login with invalid credentials
        loginPage.login(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);

        // Assert: Error message should be displayed
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be shown");
        assertNotNull(loginPage.getErrorMessage(), "Error message should not be null");
    }

    @Test
    @DisplayName("Verify login fails for locked out user")
    @Story("Login validation")
    @Description("Login should fail for locked out user with appropriate error message")
    void testLoginWithLockedUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();

        // Test: Login with locked user
        loginPage.login(TestData.LOCKED_USERNAME, TestData.LOCKED_PASSWORD);

        // Assert: Locked user error should be displayed
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be shown for locked user");
        assertTrue(loginPage.getErrorMessage().contains("locked out"), "Error should mention locked user");
    }

    @Test
    @DisplayName("Verify products page displays products after login")
    @Story("Products page")
    @Description("Products page should display all available products after successful login")
    void testProductsPageDisplay() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();

        // Assert: Multiple products should be visible
        int productCount = productsPage.getProductCount();
        assertEquals(6, productCount, "Should display exactly 6 products");
        assertNotNull(productsPage.getFirstProductName(), "Product name should not be null");
    }

    @Test
    @DisplayName("Verify product can be added to cart")
    @Story("Cart functionality")
    @Description("User should be able to add a product to cart from products page")
    void testAddProductToCart() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageLoaded();

        // Test: Add product to cart
        productsPage.addFirstProductToCart();

        // Assert: Cart badge should show 1 item
        assertEquals(1, productsPage.getCartItemsCount(), "Cart should contain 1 item");
    }
}
