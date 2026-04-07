package com.hasbyqa.tests.base;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.util.Optional;

public class AllureScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            var driver = WebDriverRunner.getWebDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot - " + context.getDisplayName(),
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );
        } catch (Exception e) {
            // Browser may already be closed
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {}

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {}

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {}
}