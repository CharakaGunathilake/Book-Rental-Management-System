# Use official Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Make Maven wrapper executable
RUN chmod +x ./mvnw

# Download all dependencies offline
RUN ./mvnw dependency:go-offline -B

# Build the Spring Boot app (skip tests for faster build)
RUN ./mvnw package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/your-app.jar"]
