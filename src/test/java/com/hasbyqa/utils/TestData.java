package com.hasbyqa.utils;

// Test data constants for Swag Labs testing
public class TestData {

    // Valid credentials
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";

    // Invalid credentials
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "wrong_password";

    // Locked user
    public static final String LOCKED_USERNAME = "locked_out_user";
    public static final String LOCKED_PASSWORD = "secret_sauce";

    // Checkout information
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSTAL_CODE = "12345";

    // Alternative checkout data
    public static final String ALT_FIRST_NAME = "Jane";
    public static final String ALT_LAST_NAME = "Smith";
    public static final String ALT_POSTAL_CODE = "54321";

    // Error messages
    public static final String INVALID_CREDENTIALS_ERROR = "Epic sadface: Username and password do not match any user in this service";
    public static final String LOCKED_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    public static final String REQUIRED_FIELD_ERROR = "Error: First Name is required";

    // Test URLs
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String PRODUCTS_URL = BASE_URL + "/inventory.html";
    public static final String CART_URL = BASE_URL + "/cart.html";
}
