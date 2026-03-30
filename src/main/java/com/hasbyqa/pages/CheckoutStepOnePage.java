package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Checkout Step One Page Object
public class CheckoutStepOnePage extends BasePage {

    // Locators
    private static final String PAGE_TITLE = ".title";
    private static final String FIRST_NAME_FIELD = "[data-test='firstName']";
    private static final String LAST_NAME_FIELD = "[data-test='lastName']";
    private static final String POSTAL_CODE_FIELD = "[data-test='postalCode']";
    private static final String CONTINUE_BUTTON = "[data-test='continue']";
    private static final String CANCEL_BUTTON = "[data-test='cancel']";
    private static final String ERROR_MESSAGE = "[data-test='error']";

    @Step("Verify checkout step one page is loaded")
    @Override
    public CheckoutStepOnePage verifyPageLoaded() {
        $(PAGE_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Enter first name: {firstName}")
    public CheckoutStepOnePage enterFirstName(String firstName) {
        $(FIRST_NAME_FIELD).setValue(firstName);
        return this;
    }

    @Step("Enter last name: {lastName}")
    public CheckoutStepOnePage enterLastName(String lastName) {
        $(LAST_NAME_FIELD).setValue(lastName);
        return this;
    }

    @Step("Enter postal code: {postalCode}")
    public CheckoutStepOnePage enterPostalCode(String postalCode) {
        $(POSTAL_CODE_FIELD).setValue(postalCode);
        return this;
    }

    @Step("Click continue button")
    public void clickContinueButton() {
        $(CONTINUE_BUTTON).click();
    }

    @Step("Fill checkout info - FirstName: {firstName}, LastName: {lastName}, PostalCode: {postalCode}")
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return $(ERROR_MESSAGE).getText();
    }

    @Step("Click cancel button")
    public void clickCancelButton() {
        $(CANCEL_BUTTON).click();
    }
}
