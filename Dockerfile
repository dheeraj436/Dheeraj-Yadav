# Use an official Java runtime as a base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
# Note: Ensure you've built the JAR using 'mvn package' or './gradlew build'
COPY target/*.war app.jar

EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]