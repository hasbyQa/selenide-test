package com.hasbyqa.tests.base;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

// Takes and attaches screenshot to Allure after every test (pass or fail)
public class AllureScreenshotExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        try {
            if (WebDriverRunner.hasWebDriverStarted()) {
                var driver = WebDriverRunner.getWebDriver();
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                String label = context.getTestMethod()
                        .map(m -> m.getName())
                        .orElse("screenshot");
                Allure.addAttachment(
                        label,
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );
            }
        } catch (Exception ignored) {
        }
    }
}