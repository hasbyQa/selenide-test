package com.hasbyqa.pages.base;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Base page object - common methods for all pages
public class BasePage {

    @Step("Verify page loaded successfully")
    public BasePage verifyPageLoaded() {
        // To be overridden in child classes
        return this;
    }

    @Step("Click on element {locator}")
    protected BasePage click(String locator) {
        $(locator).click();
        return this;
    }

    @Step("Type text {text} in field {locator}")
    protected BasePage typeText(String locator, String text) {
        $(locator).setValue(text);
        return this;
    }

    @Step("Get text from element {locator}")
    protected String getText(String locator) {
        return $(locator).getText();
    }

    @Step("Wait for element {locator} to be visible")
    protected SelenideElement waitForElement(String locator) {
        return $(locator).shouldBe(com.codeborne.selenide.Condition.visible);
    }

    @Step("Check if element {locator} is visible")
    protected boolean isElementVisible(String locator) {
        try {
            return $(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get elements count for locator {locator}")
    protected int getElementsCount(String locator) {
        return $$(locator).size();
    }
}
