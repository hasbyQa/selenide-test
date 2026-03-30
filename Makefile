.PHONY: help build test test-smoke test-regression test-headless clean report docker-build docker-run docker-clean install

help:
	@echo "Selenide Automation Testing - Swag Labs"
	@echo "======================================"
	@echo ""
	@echo "Available commands:"
	@echo "  make install          - Install dependencies"
	@echo "  make test             - Run all tests"
	@echo "  make test-smoke       - Run smoke tests only"
	@echo "  make test-regression  - Run regression tests only"
	@echo "  make test-headless    - Run tests in headless mode"
	@echo "  make report           - Generate and serve Allure report"
	@echo "  make clean            - Clean build artifacts"
	@echo "  make docker-build     - Build Docker image"
	@echo "  make docker-run       - Run tests in Docker"
	@echo "  make docker-clean     - Clean Docker resources"
	@echo "  make build            - Build the project"

install:
	mvn clean install

build:
	mvn clean package -DskipTests

test:
	mvn clean test

test-smoke:
	mvn test -Dtest=LoginSmokeTest

test-regression:
	mvn test -Dtest=CartCheckoutRegressionTest

test-headless:
	mvn clean test -Dselenide.headless=true

clean:
	mvn clean
	rm -rf allure-results allure-report logs target

report:
	mvn allure:report allure:serve

docker-build:
	docker build -t selenide-tests:latest .

docker-run: docker-build
	docker run --rm -v $(PWD)/allure-results:/app/allure-results selenide-tests

docker-clean:
	docker rmi selenide-tests:latest || true
	docker system prune -f
