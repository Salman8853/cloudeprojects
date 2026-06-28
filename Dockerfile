# Stage 1: Build the application
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR
RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
