# Selenide Automation Testing - Swag Labs

A comprehensive UI automation testing project for [Swag Labs](https://www.saucedemo.com) using Selenide, JUnit 5, and Allure Reports.

## 📋 Project Overview

This project demonstrates best practices in UI automation testing using:
- **Selenide**: Fluent API for browser automation
- **JUnit 5**: Modern testing framework
- **Page Object Model (POM)**: Clean test structure
- **Allure Reports**: Comprehensive test reporting
- **Docker**: Containerized test execution
- **GitHub Actions**: CI/CD pipeline

## 🎯 Features

- ✅ Smoke tests for login and products page
- ✅ Regression tests for cart and checkout functionality
- ✅ Page Object Model for maintainability
- ✅ Allure reporting with screenshots on failure
- ✅ Headless browser support (Chrome, Firefox)
- ✅ Docker containerization
- ✅ GitHub Actions CI/CD pipeline
- ✅ Automatic screenshot capture
- ✅ Soft and hard assertions
- ✅ Fluent API for readable tests

## 📁 Project Structure

```
selenide-test/
├── src/
│   ├── main/java/com/hasbyqa/
│   │   ├── pages/
│   │   │   ├── base/
│   │   │   │   └── BasePage.java          # Base class for all pages
│   │   │   ├── LoginPage.java              # Login page object
│   │   │   ├── ProductsPage.java           # Products inventory page
│   │   │   ├── CartPage.java               # Shopping cart page
│   │   │   ├── CheckoutStepOnePage.java    # Checkout step 1 (info)
│   │   │   ├── CheckoutStepTwoPage.java    # Checkout step 2 (review)
│   │   │   └── CheckoutCompletePage.java   # Order completion page
│   │   └── utils/
│   │       └── TestData.java               # Test data constants
│   │
│   └── test/java/com/hasbyqa/
│       ├── base/
│       │   └── BaseTest.java               # Base test configuration
│       ├── smoke/
│       │   └── LoginSmokeTest.java         # Smoke tests
│       └── regression/
│           └── CartCheckoutRegressionTest.java  # Regression tests
│
├── pom.xml                      # Maven configuration
├── Dockerfile                   # Docker image configuration
├── docker-compose.yml           # Docker compose setup
├── allure.yml                   # Allure configuration
├── .github/
│   └── workflows/
│       └── ci-pipeline.yml      # GitHub Actions CI/CD
└── README.md                    # This file
```

## 🧪 Test Scenarios

### Smoke Tests (Login + Products Page)
1. **Successful Login** - Valid credentials login
2. **Invalid Login** - Invalid credentials error handling
3. **Locked User** - Locked user error message
4. **Products Display** - Verify 6 products shown
5. **Add to Cart** - Add product to cart from inventory

### Regression Tests (Cart + Checkout)
1. **Cart Item** - Verify product in cart
2. **Multiple Items** - Add multiple products
3. **Complete Checkout** - Full checkout flow
4. **Validation** - Required field validation
5. **Continue Shopping** - Navigate back to products
6. **Alternative Data** - Checkout with different user info

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.8+
- Docker & Docker Compose (for containerized execution)
- Chrome or Firefox browser

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/hasbyQa/selenide-test.git
   cd selenide-test
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

## 🏃 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
# Run smoke tests
mvn test -Dtest=LoginSmokeTest

# Run regression tests
mvn test -Dtest=CartCheckoutRegressionTest
```

### Run Tests in Headless Mode
```bash
mvn clean test -Dselenide.headless=true
```

### Run Tests with Different Browser
```bash
mvn clean test -Dselenide.browser=firefox
```

## 📊 Generating Reports

### Allure Report
```bash
# Generate Allure report
mvn allure:report

# Serve report locally
mvn allure:serve
```

The Allure report will be available at `target/site/allure-maven-plugin/index.html`

## 🐳 Docker Execution

### Build Docker Image
```bash
docker build -t selenide-tests .
```

### Run Tests in Docker
```bash
docker run --rm -v $(pwd)/allure-results:/app/allure-results selenide-tests
```

### Using Docker Compose
```bash
docker-compose up --build
```

## 🔄 CI/CD Pipeline (GitHub Actions)

The project includes an automated CI/CD pipeline that:
- Runs on every push and pull request
- Executes all test suites
- Generates Allure reports
- Uploads artifacts (reports, screenshots)
- Publishes test results

**Workflow file:** `.github/workflows/ci-pipeline.yml`

### Workflow Steps:
1. Checkout code
2. Set up Java environment
3. Run Selenide tests
4. Generate Allure report
5. Publish test results
6. Upload artifacts
7. Send notifications on failure

## 📝 Test Data

**Valid User:**
- Username: `standard_user`
- Password: `secret_sauce`

**Locked User:**
- Username: `locked_out_user`
- Password: `secret_sauce`

**Checkout Info:**
- Name: John Doe
- Postal Code: 12345

All test data is defined in `TestData.java` for easy maintenance.

## 🏗️ Architecture & Best Practices

### Page Object Model (POM)
- Separates test logic from page interactions
- Single source of truth for locators
- Easy maintenance and updates

### Base Classes
- **BasePage**: Common page methods (click, type, wait)
- **BaseTest**: Test setup/teardown, configuration

### Test Organization
- **Smoke Tests**: Critical path testing
- **Regression Tests**: Comprehensive functionality testing
- **Separation of Concerns**: Each page has its responsibility

### Assertions
- Hard assertions for critical validations
- Soft assertions for non-critical checks
- Meaningful assertion messages

## 🔧 Configuration

### Browser Configuration (src/test/java/com/hasbyqa/tests/base/BaseTest.java)
```java
Configuration.headless = true;           // Enable headless mode
Configuration.browser = "chrome";        // Browser choice
Configuration.timeout = 10000;           // Wait timeout (ms)
Configuration.screenshotOnFailure = true; // Auto screenshots
```

### Selenide Features
- **Implicit Waits**: Built-in 10-second timeout
- **Screenshots**: Automatic on failure
- **Page Source**: Saved on failure
- **Fluent API**: Chain methods for readability

## 📋 Dependencies

- **Selenide 6.17.0** - Browser automation
- **JUnit 5** - Testing framework
- **Allure 2.22.1** - Test reporting
- **WebDriver Manager** - Driver management
- **Lombok** - Reduce boilerplate code

## 🎓 Learning Objectives

By studying this project, you will learn:
1. ✅ Page Object Model pattern
2. ✅ Selenide fluent API usage
3. ✅ JUnit 5 test organization
4. ✅ Test data management
5. ✅ CI/CD integration
6. ✅ Docker containerization
7. ✅ Allure reporting
8. ✅ GitHub Actions automation
9. ✅ Assertion strategies
10. ✅ Test reporting best practices

## 🚨 Troubleshooting

### Tests Fail to Run
```bash
# Clear Maven cache
mvn clean

# Reinstall dependencies
mvn install
```

### Browser Driver Issues
The project uses WebDriver Manager, which automatically downloads drivers.

### Report Generation Fails
```bash
# Ensure Allure CLI is installed
npm install -g allure-commandline

# Then regenerate
mvn allure:report
```

## 📞 Support

For issues or questions:
1. Check test logs in `target/surefire-reports/`
2. Review Allure report for failure details
3. Check screenshots in test artifacts

## 📄 License

This project is for educational purposes.

## 👨‍💼 Author

Created as a comprehensive guide for QA automation testing with Selenide.

---

**Happy Testing! 🧪✨**
