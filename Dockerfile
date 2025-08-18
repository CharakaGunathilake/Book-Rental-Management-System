# Use Debian-based JDK 21 image (not Alpine)
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Ensure mvnw is executable
RUN chmod +x mvnw

# Download dependencies offline
RUN ./mvnw dependency:go-offline -B

# Package the app (skip tests if needed)
RUN ./mvnw package -DskipTests

# Expose port
EXPOSE 8080

# Run the Spring Boot app
RUN ./mvnw package -DskipTests
CMD ["sh", "-c", "java -jar target/*.jar"]

