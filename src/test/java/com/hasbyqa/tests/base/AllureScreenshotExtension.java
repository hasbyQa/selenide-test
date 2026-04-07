package com.hasbyqa.tests.base;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AllureScreenshotExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        // Only take screenshot on failure
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(context);
        }
    }

    private void takeScreenshot(ExtensionContext context) {
        try {
            if (!WebDriverRunner.hasWebDriverStarted()) return;
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            String name = "FAILED - " + context.getDisplayName();
            Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), "png");
        } catch (Exception ignored) {
        }
    }
}