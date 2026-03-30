package com.hasbyqa.tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

// Base class for all tests - handles initialization and cleanup
public class BaseTest {

    protected static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        // Configure Selenide for headless browser execution
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // 10 seconds implicit wait
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = true;
        Configuration.screenshotOnFailure = true;
        Configuration.savePageSource = true;

        // Open the base URL
        open(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser and clear session
        closeWebDriver();
    }

    // Helper method for taking screenshots on failure
    @Step("Take screenshot for failure")
    protected void takeScreenshot() {
        Selenide.screenshot(String.valueOf(System.currentTimeMillis()));
    }

    // Helper method for setting headless mode
    protected void setHeadless(boolean headless) {
        Configuration.headless = headless;
    }

    // Helper method for setting browser
    protected void setBrowser(String browserName) {
        Configuration.browser = browserName;
    }
}
