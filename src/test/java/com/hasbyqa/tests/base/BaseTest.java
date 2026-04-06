package com.hasbyqa.tests.base;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    protected static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        // Attach Allure listener - captures screenshots and logs on failure automatically
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.pageLoadStrategy = "normal";
        Configuration.headless = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        Configuration.browserCapabilities = options;

        open(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }
}