# Development Guide - Understanding the Codebase

## 📚 Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [File Structure](#file-structure)
3. [Key Classes](#key-classes)
4. [How to Add Tests](#how-to-add-tests)
5. [How to Add Page Objects](#how-to-add-page-objects)
6. [Configuration](#configuration)

## Architecture Overview

### High-Level Design

```
┌─────────────────────────────────────────────────────┐
│                    Test Classes                      │
│  (LoginSmokeTest, CartCheckoutRegressionTest)       │
└────────────────────┬────────────────────────────────┘
                     │ extends
┌────────────────────▼────────────────────────────────┐
│                    BaseTest                          │
│  (Setup, Teardown, Browser Config)                 │
└────────────────────┬────────────────────────────────┘
                     │ uses
┌────────────────────▼────────────────────────────────┐
│                Page Objects                          │
│  (LoginPage, ProductsPage, CartPage, etc.)         │
└────────────────────┬────────────────────────────────┘
                     │ extends
┌────────────────────▼────────────────────────────────┐
│                    BasePage                          │
│  (Common page methods: click, type, wait)           │
└─────────────────────────────────────────────────────┘
```

### Design Patterns Used

1. **Page Object Model (POM)**
   - Each page has its own class
   - Locators are private constants
   - Methods represent page actions
   - No assertions in page objects

2. **Base Class Pattern**
   - BasePage: Common page methods
   - BaseTest: Common test setup/teardown

3. **Fluent API**
   - Methods return `this` for chaining
   - Readable test code
   - Example: `page.enterUsername("user").enterPassword("pass").clickLoginButton()`

## File Structure

```
selenide-test/
├── src/
│   ├── main/java/com/hasbyqa/
│   │   ├── pages/
│   │   │   ├── base/
│   │   │   │   └── BasePage.java           # Base class with common methods
│   │   │   ├── LoginPage.java              # Login page operations
│   │   │   ├── ProductsPage.java           # Product inventory operations
│   │   │   ├── CartPage.java               # Shopping cart operations
│   │   │   ├── CheckoutStepOnePage.java    # Checkout info page
│   │   │   ├── CheckoutStepTwoPage.java    # Checkout review page
│   │   │   └── CheckoutCompletePage.java   # Order completion
│   │   └── utils/
│   │       └── TestData.java               # Test data constants
│   │
│   └── test/java/com/hasbyqa/
│       ├── base/
│       │   └── BaseTest.java               # Base test class
│       ├── smoke/
│       │   └── LoginSmokeTest.java         # Smoke tests
│       └── regression/
│           └── CartCheckoutRegressionTest.java  # Regression tests
│
├── pom.xml                  # Maven dependencies and plugins
├── Dockerfile               # Docker image definition
├── docker-compose.yml       # Docker compose configuration
├── Makefile                 # Useful commands
├── setup.sh                 # Automated setup script
├── README.md                # Full documentation
├── QUICKSTART.md            # Quick start guide
└── METRICS.md               # Rubric compliance
```

## Key Classes

### 1. BaseTest (src/test/java/com/hasbyqa/tests/base/BaseTest.java)

**Purpose**: Common setup and teardown for all tests

**Key Methods**:
- `setUp()` - Configures Selenide before each test
- `tearDown()` - Closes browser after each test
- Helper methods for browser configuration

**Configuration Options**:
```java
Configuration.headless = true;              // Headless mode
Configuration.browser = "chrome";           // Browser choice
Configuration.timeout = 10000;              // Wait timeout
Configuration.screenshots = true;           // Enable screenshots
```

### 2. BasePage (src/main/java/com/hasbyqa/pages/base/BasePage.java)

**Purpose**: Common methods for all page objects

**Key Methods**:
- `click(String locator)` - Click on element
- `typeText(String locator, String text)` - Enter text
- `getText(String locator)` - Get element text
- `waitForElement(String locator)` - Wait for visibility
- `isElementVisible(String locator)` - Check visibility
- `getElementsCount(String locator)` - Count elements

### 3. LoginPage (src/main/java/com/hasbyqa/pages/LoginPage.java)

**Purpose**: Encapsulate login page operations

**Key Methods**:
```java
public LoginPage enterUsername(String username)    // Enter username
public LoginPage enterPassword(String password)    // Enter password
public void clickLoginButton()                     // Click login
public void login(String username, String password) // Full login flow
public String getErrorMessage()                    // Get error text
public boolean isErrorMessageDisplayed()           // Check error visible
```

### 4. TestData (src/test/java/com/hasbyqa/utils/TestData.java)

**Purpose**: Centralized test data management

**Contains**:
- Valid credentials
- Invalid credentials
- Locked user credentials
- Checkout information
- Error messages
- Test URLs

## How to Add Tests

### Step 1: Create Test Class

Create a new file in appropriate package:
- Smoke tests: `src/test/java/com/hasbyqa/tests/smoke/`
- Regression tests: `src/test/java/com/hasbyqa/tests/regression/`

### Step 2: Extend BaseTest

```java
package com.hasbyqa.tests.smoke;

import com.hasbyqa.tests.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class NewTest extends BaseTest {
    // Inherits setUp() and tearDown()
}
```

### Step 3: Add Test Method

```java
@Test
@DisplayName("Verify feature works correctly")
void testFeature() {
    // Arrange
    LoginPage loginPage = new LoginPage();
    loginPage.verifyPageLoaded();
    
    // Act
    loginPage.login("standard_user", "secret_sauce");
    
    // Assert
    ProductsPage productsPage = new ProductsPage();
    productsPage.verifyPageLoaded();
    assertEquals(6, productsPage.getProductCount());
}
```

## How to Add Page Objects

### Step 1: Create Page Class

```java
package com.hasbyqa.pages;

import com.hasbyqa.pages.base.BasePage;
import io.qameta.allure.Step;

public class NewPage extends BasePage {
    // Define locators
    private static final String ELEMENT = "css-or-xpath-locator";
    
    // Override verifyPageLoaded
    @Override
    public NewPage verifyPageLoaded() {
        $(ELEMENT).shouldBe(visible);
        return this;
    }
    
    // Add page methods
    @Step("Description of action")
    public NewPage doSomething() {
        // Implementation
        return this;
    }
}
```

### Step 2: Follow Locator Best Practices

```java
// ✅ Good - Use data-test attributes
private static final String LOGIN_BUTTON = "[data-test='login-button']";

// ✅ Good - Use CSS selectors
private static final String PRODUCT = ".inventory_item";

// ✅ Good - Use IDs
private static final String USERNAME = "#username";

// ❌ Avoid - Hardcoding XPaths
private static final String BUTTON = "//button[@class='btn']";
```

### Step 3: Implement Methods

```java
// ✅ Return this for fluent API
public NewPage doSomething(String value) {
    $(ELEMENT).setValue(value);
    return this;
}

// ✅ Use Step annotation for reporting
@Step("Do something")
public void action() {
    $(BUTTON).click();
}

// ✅ No assertions in page objects - return values instead
public String getText() {
    return getText(ELEMENT);
}
```

## Configuration

### Selenide Configuration (BaseTest.java)

```java
Configuration.headless = true;              // Headless browser
Configuration.browser = "chrome";           // Browser type
Configuration.browserSize = "1920x1080";    // Window size
Configuration.timeout = 10000;              // Implicit wait (ms)
Configuration.pageLoadStrategy = "eager";   // Page load strategy
Configuration.screenshots = true;           // Enable screenshots
Configuration.screenshotOnFailure = true;   // Screenshot on failure
Configuration.savePageSource = true;        // Save HTML on failure
```

### Maven Configuration (pom.xml)

Key plugins:
- **maven-compiler-plugin**: Compilation settings
- **maven-surefire-plugin**: Test execution
- **allure-maven**: Report generation

### GitHub Actions (`.github/workflows/ci-pipeline.yml`)

Runs on:
- Every push to main/develop
- Every pull request
- Configurable schedule (optional)

## Best Practices

### 1. Test Organization
```
✅ One test class per feature/page
✅ Smoke tests for critical paths
✅ Regression tests for complete flows
✅ Descriptive test names
```

### 2. Page Objects
```
✅ One class per page
✅ Locators as private constants
✅ Methods represent user actions
✅ Return this for fluent API
❌ No assertions in page objects
```

### 3. Test Data
```
✅ Centralized in TestData class
✅ Constants for all test data
✅ Easy to update
✅ Single source of truth
```

### 4. Assertions
```
✅ Meaningful assertion messages
✅ Hard assertions for critical checks
✅ Appropriate assertion methods
❌ Too many assertions per test
```

### 5. Code Readability
```
✅ Clear variable names
✅ Short single-line comments
✅ Fluent method chains
✅ Logical method ordering
```

## Common Tasks

### Run specific test
```bash
mvn test -Dtest=LoginSmokeTest#testSuccessfulLogin
```

### Run with different browser
```bash
mvn test -Dselenide.browser=firefox
```

### Run in GUI mode (see browser)
```bash
mvn test -Dselenide.headless=false
```

### Generate report
```bash
mvn allure:report
mvn allure:serve
```

### Run in Docker
```bash
docker-compose up --build
```

## Troubleshooting

### Tests fail with timeout
- Increase `Configuration.timeout`
- Check internet connection
- Check if website is accessible

### Screenshots not captured
- Check `Configuration.screenshotOnFailure = true`
- Verify `target/screenshots/` directory exists

### Allure report not generating
- Run `mvn allure:report`
- Check `allure-results/` directory

### Docker build fails
- Run `docker system prune`
- Check Docker is running
- Verify internet connection for dependencies

## Next Steps

1. **Explore the Code**: Read through each page object
2. **Run Tests**: Execute `mvn clean test`
3. **View Reports**: Run `mvn allure:serve`
4. **Add Tests**: Create new test classes
5. **Customize**: Modify for your needs

---

**Happy Coding! 🚀**
