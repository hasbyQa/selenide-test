package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Checkout Step Two Page Object
public class CheckoutStepTwoPage extends BasePage {

    // Locators
    private static final String PAGE_TITLE = ".title";
    private static final String ITEM_TOTAL = ".summary_subtotal_label";
    private static final String TAX = ".summary_tax_label";
    private static final String TOTAL = ".summary_total_label";
    private static final String FINISH_BUTTON = "[data-test='finish']";
    private static final String CANCEL_BUTTON = "[data-test='cancel']";

    @Step("Verify checkout step two page is loaded")
    @Override
    public CheckoutStepTwoPage verifyPageLoaded() {
        $(PAGE_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Get item total")
    public String getItemTotal() {
        return getText(ITEM_TOTAL);
    }

    @Step("Get tax amount")
    public String getTaxAmount() {
        return getText(TAX);
    }

    @Step("Get total amount")
    public String getTotalAmount() {
        return getText(TOTAL);
    }

    @Step("Click finish button")
    public void clickFinishButton() {
        $(FINISH_BUTTON).click();
    }

    @Step("Click cancel button")
    public void clickCancelButton() {
        $(CANCEL_BUTTON).click();
    }
}
