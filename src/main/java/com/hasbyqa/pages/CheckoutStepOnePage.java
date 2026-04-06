package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;

public class CheckoutStepOnePage extends BasePage {

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
        webdriver().shouldHave(urlContaining("checkout-step-one"));
        $(PAGE_TITLE).shouldBe(visible).shouldHave(text("Checkout: Your Information"));
        $(FIRST_NAME_FIELD).shouldBe(visible).shouldBe(enabled);
        return this;
    }

    /**
     * Sets a value on a React-controlled input using JS to trigger
     * React's synthetic onChange event, then fires a native input event
     * so React registers the change before form submission.
     */
    private void setReactInputValue(String selector, String value) {
        var element = $(selector).shouldBe(visible).shouldBe(enabled);
        // Use JS to set the value and fire input + change events React listens to
        executeJavaScript(
                "var el = arguments[0];" +
                        "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "nativeInputValueSetter.call(el, arguments[1]);" +
                        "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('change', { bubbles: true }));",
                element.getWrappedElement(), value
        );
    }

    @Step("Enter first name: {firstName}")
    public CheckoutStepOnePage enterFirstName(String firstName) {
        setReactInputValue(FIRST_NAME_FIELD, firstName);
        return this;
    }

    @Step("Enter last name: {lastName}")
    public CheckoutStepOnePage enterLastName(String lastName) {
        setReactInputValue(LAST_NAME_FIELD, lastName);
        return this;
    }

    @Step("Enter postal code: {postalCode}")
    public CheckoutStepOnePage enterPostalCode(String postalCode) {
        setReactInputValue(POSTAL_CODE_FIELD, postalCode);
        return this;
    }

    @Step("Fill checkout info - FirstName: {firstName}, LastName: {lastName}, PostalCode: {postalCode}")
    public CheckoutStepOnePage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return this;
    }

    @Step("Click continue button")
    public void clickContinueButton() {
        $(CONTINUE_BUTTON).shouldBe(visible).shouldBe(enabled).click();
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