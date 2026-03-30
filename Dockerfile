# Use official Maven image with OpenJDK 11
FROM maven:3.8.1-openjdk-11

# Set working directory
WORKDIR /app

# Copy pom.xml
COPY pom.xml .

# Download dependencies
RUN mvn dependency:resolve

# Copy project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Install Allure CLI for report generation
RUN apt-get update && apt-get install -y npm && npm install -g allure-commandline

# Run tests and generate Allure report
CMD ["sh", "-c", "mvn clean test && mvn allure:report"]
