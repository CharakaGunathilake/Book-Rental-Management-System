# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven or Gradle wrapper and pom/build files for caching dependencies
COPY pom.xml mvnw* ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the application
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Set environment variables (optional defaults for local dev)
ENV PORT=8080
ENV PGHOST=postgres
ENV PGPORT=5432
ENV PGDATABASE=book_rental_db
ENV PGUSER=postgres
ENV PGPASSWORD=1234

# Expose the port
EXPOSE 8080

# Entry point
ENTRYPOINT ["java", "-jar", "target/book-rental-app-0.0.1-SNAPSHOT.jar"]
