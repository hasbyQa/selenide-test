# Quick Start Guide

## 🚀 5-Minute Setup

### Step 1: Clone the Repository
```bash
cd ~/Desktop/Amali/QA
git clone https://github.com/hasbyQa/selenide-test.git
cd selenide-test
```

### Step 2: Run the Setup Script
```bash
bash setup.sh
```

Or manually:

### Step 2 (Manual): Install and Run Tests
```bash
# Install dependencies
mvn clean install

# Run all tests
mvn clean test

# Generate Allure report
mvn allure:report

# View the report
mvn allure:serve
```

## 📊 Test Execution Options

### Run Smoke Tests Only
```bash
mvn test -Dtest=LoginSmokeTest
```

### Run Regression Tests Only
```bash
mvn test -Dtest=CartCheckoutRegressionTest
```

### Run with Headless Browser
```bash
mvn clean test -Dselenide.headless=true
```

### Run with Firefox
```bash
mvn clean test -Dselenide.browser=firefox
```

## 🐳 Docker Execution

### Using Docker Compose (Recommended)
```bash
docker-compose up --build
```

### Using Makefile Commands
```bash
# Build Docker image
make docker-build

# Run tests in Docker
make docker-run

# Clean Docker resources
make docker-clean
```

## 📊 View Test Reports

### Generate and View Allure Report
```bash
mvn allure:serve
```
This will automatically open the report in your default browser.

### View in Specific Directory
```bash
# Report location
target/site/allure-maven-plugin/index.html
```

## 🧪 Test Coverage

### Smoke Tests (5 tests)
- ✅ Login with valid credentials
- ✅ Login with invalid credentials
- ✅ Login with locked user
- ✅ Products page display
- ✅ Add product to cart

### Regression Tests (6 tests)
- ✅ Product in cart verification
- ✅ Multiple products in cart
- ✅ Complete checkout flow
- ✅ Required field validation
- ✅ Continue shopping navigation
- ✅ Checkout with alternative data

**Total: 11 test cases**

## 📁 Key Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies and build configuration |
| `src/main/java/com/hasbyqa/pages/` | Page Object Model classes |
| `src/test/java/com/hasbyqa/tests/` | Test cases and base test class |
| `src/test/java/com/hasbyqa/utils/TestData.java` | Test data constants |
| `.github/workflows/ci-pipeline.yml` | GitHub Actions CI/CD |
| `Dockerfile` | Docker container setup |
| `docker-compose.yml` | Docker Compose configuration |

## 🔐 Test Credentials

### Standard User (Use this for testing)
- Username: `standard_user`
- Password: `secret_sauce`

### Other Available Users
- **Problem User**: `problem_user` / `secret_sauce`
- **Performance Glitch User**: `performance_glitch_user` / `secret_sauce`
- **Locked User**: `locked_out_user` / `secret_sauce` (Login fails)

## ⚙️ Configuration

### Selenide Settings (BaseTest.java)
```java
Configuration.headless = true;        // Browser mode
Configuration.browser = "chrome";     // Browser selection
Configuration.timeout = 10000;        // Implicit wait (ms)
Configuration.screenshots = true;     // Take screenshots
Configuration.screenshotOnFailure = true; // On failure capture
```

### Test Data (TestData.java)
```java
VALID_USERNAME = "standard_user"
VALID_PASSWORD = "secret_sauce"
FIRST_NAME = "John"
LAST_NAME = "Doe"
POSTAL_CODE = "12345"
```

## 🐛 Troubleshooting

### Issue: Tests fail to run
**Solution**: Clear Maven cache and reinstall
```bash
mvn clean install
```

### Issue: Browser driver not found
**Solution**: WebDriver Manager handles this automatically. If it fails:
```bash
mvn dependency:resolve
```

### Issue: Cannot generate Allure report
**Solution**: Install Allure CLI
```bash
npm install -g allure-commandline
mvn allure:report
```

### Issue: Docker build fails
**Solution**: Update Docker and try again
```bash
docker --version  # Should be Docker 20.10+
docker-compose --version  # Should be 1.29+
```

## 📈 CI/CD Integration

The project includes GitHub Actions automation:
- Runs on every commit (main and develop branches)
- Executes all test suites
- Generates Allure reports
- Uploads artifacts
- Publishes results

**Workflow file**: `.github/workflows/ci-pipeline.yml`

## 📚 Best Practices Used

1. **Page Object Model** - Maintainable and scalable
2. **Fluent API** - Readable and chainable methods
3. **Separation of Concerns** - Clear test organization
4. **Test Data Management** - Centralized constants
5. **Base Classes** - No code duplication
6. **Allure Reporting** - Rich, visual test reports
7. **CI/CD Pipeline** - Automated test execution
8. **Docker Support** - Isolated test environments
9. **Assertion Strategy** - Clear, meaningful assertions
10. **Code Comments** - Clean, understandable code

## 🎓 Learning Path

1. Start with `BaseTest.java` - Understand test setup
2. Review `LoginPage.java` - Learn Page Object Model
3. Check `LoginSmokeTest.java` - Simple test example
4. Study `CartCheckoutRegressionTest.java` - Complex test scenarios
5. Explore GitHub Actions workflow - CI/CD automation

## 💡 Tips

- Always verify page loads with `verifyPageLoaded()`
- Use fluent API for method chaining
- Keep test data in `TestData.java`
- Add `@Step` annotations for detailed reports
- Use meaningful assertion messages
- Check Allure reports for failure analysis
- Screenshots are auto-captured on failure

## 🆘 Getting Help

1. Check the main **README.md** for detailed documentation
2. Review **Allure reports** for failure details
3. Check **Maven logs** in terminal output
4. Look at **test screenshots** in Allure report
5. Review **GitHub Actions logs** for CI/CD issues

---

**You're all set! Happy testing! 🚀**
