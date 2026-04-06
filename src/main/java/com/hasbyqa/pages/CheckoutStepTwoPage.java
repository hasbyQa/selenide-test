package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Selenide.webdriver;

public class CheckoutStepTwoPage extends BasePage {

    private static final String PAGE_TITLE = ".title";
    private static final String ITEM_TOTAL = ".summary_subtotal_label";
    private static final String TAX = ".summary_tax_label";
    private static final String TOTAL = ".summary_total_label";
    private static final String FINISH_BUTTON = "[data-test='finish']";
    private static final String CANCEL_BUTTON = "[data-test='cancel']";

    @Step("Verify checkout step two page is loaded")
    @Override
    public CheckoutStepTwoPage verifyPageLoaded() {
        webdriver().shouldHave(urlContaining("checkout-step-two"));
        $(PAGE_TITLE).shouldBe(visible).shouldHave(text("Checkout: Overview"));
        $(FINISH_BUTTON).shouldBe(visible).shouldBe(enabled);
        return this;
    }

    @Step("Get item total")
    public String getItemTotal() {
        return $(ITEM_TOTAL).shouldBe(visible).getText();
    }

    @Step("Get tax amount")
    public String getTaxAmount() {
        return $(TAX).shouldBe(visible).getText();
    }

    @Step("Get total amount")
    public String getTotalAmount() {
        return $(TOTAL).shouldBe(visible).getText();
    }

    @Step("Click finish button")
    public void clickFinishButton() {
        $(FINISH_BUTTON).shouldBe(visible).shouldBe(enabled).click();
    }

    @Step("Click cancel button")
    public void clickCancelButton() {
        $(CANCEL_BUTTON).click();
    }
}