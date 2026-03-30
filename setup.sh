#!/bin/bash

# Selenide Test Setup Script
# This script sets up the project and runs tests

set -e

echo "╔════════════════════════════════════════════════════════╗"
echo "║  Selenide Automation Testing - Swag Labs Setup        ║"
echo "╚════════════════════════════════════════════════════════╝"
echo ""

# Check Java installation
echo "✓ Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo "✗ Java is not installed. Please install Java 11+"
    exit 1
fi
JAVA_VERSION=$(java -version 2>&1 | head -1)
echo "  Java version: $JAVA_VERSION"
echo ""

# Check Maven installation
echo "✓ Checking Maven installation..."
if ! command -v mvn &> /dev/null; then
    echo "✗ Maven is not installed. Please install Maven 3.8+"
    exit 1
fi
MVN_VERSION=$(mvn -v 2>&1 | head -1)
echo "  Maven version: $MVN_VERSION"
echo ""

# Install dependencies
echo "✓ Installing dependencies..."
mvn clean install -q
echo "  Dependencies installed successfully!"
echo ""

# Run tests
echo "✓ Running tests..."
mvn clean test
echo ""

# Generate report
echo "✓ Generating Allure report..."
mvn allure:report -q
echo "  Report generated at: target/site/allure-maven-plugin/index.html"
echo ""

echo "╔════════════════════════════════════════════════════════╗"
echo "║  Setup completed successfully! ✓                      ║"
echo "║  To view the report, run: mvn allure:serve            ║"
echo "╚════════════════════════════════════════════════════════╝"
