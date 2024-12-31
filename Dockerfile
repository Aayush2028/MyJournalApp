# Stage 1: Build
FROM maven:3.8.6-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/my-spring-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
