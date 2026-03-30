package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Login Page Object
public class LoginPage extends BasePage {

    // Locators
    private static final String USERNAME_FIELD = "[data-test='username']";
    private static final String PASSWORD_FIELD = "[data-test='password']";
    private static final String LOGIN_BUTTON = "[data-test='login-button']";
    private static final String ERROR_MESSAGE = "[data-test='error']";
    private static final String LOGO = ".login_logo";

    @Step("Verify login page is loaded")
    @Override
    public LoginPage verifyPageLoaded() {
        $(LOGO).shouldBe(visible);
        return this;
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        $(USERNAME_FIELD).setValue(username);
        return this;
    }

    @Step("Enter password: {password}")
    public LoginPage enterPassword(String password) {
        $(PASSWORD_FIELD).setValue(password);
        return this;
    }

    @Step("Click login button")
    public void clickLoginButton() {
        $(LOGIN_BUTTON).click();
    }

    @Step("Login with credentials - Username: {username}, Password: {password}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return $(ERROR_MESSAGE).getText();
    }

    @Step("Verify error message is visible")
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }
}
