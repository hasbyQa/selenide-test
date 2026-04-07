package com.hasbyqa.tests.base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(AllureScreenshotExtension.class)
public class BaseTest {

    protected static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.pageLoadStrategy = "normal";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--enable-javascript");
        Configuration.browserCapabilities = options;

        open(BASE_URL);
        clearBrowserCookies();
        executeJavaScript("window.localStorage.clear();");
        executeJavaScript("window.sessionStorage.clear();");
        open(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}