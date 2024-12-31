# Stage 1: Build
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy and build the application
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/journalapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
