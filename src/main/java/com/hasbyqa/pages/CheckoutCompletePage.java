package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;

public class CheckoutCompletePage extends BasePage {

    private static final String SUCCESS_MESSAGE = ".complete-header";
    private static final String ORDER_NUMBER = ".complete-text";
    private static final String BACK_HOME_BUTTON = "[data-test='back-to-products']";

    @Step("Verify order completion page is loaded")
    @Override
    public CheckoutCompletePage verifyPageLoaded() {
        webdriver().shouldHave(urlContaining("checkout-complete"));
        $(SUCCESS_MESSAGE).shouldBe(visible);
        return this;
    }

    @Step("Get success message")
    public String getSuccessMessage() {
        return getText(SUCCESS_MESSAGE);
    }

    @Step("Get order completion text")
    public String getCompletionText() {
        return getText(ORDER_NUMBER);
    }

    @Step("Click back to home button")
    public void clickBackToHomeButton() {
        $(BACK_HOME_BUTTON).click();
    }

    @Step("Verify order completed successfully")
    public boolean isOrderCompleted() {
        return isElementVisible(SUCCESS_MESSAGE);
    }
}