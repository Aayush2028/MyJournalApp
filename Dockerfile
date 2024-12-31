# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /journalapp

# Copy the JAR file from the target directory into the container
COPY target/journalapp-0.0.1-SNAPSHOT.jar journalapp.jar

# Expose the port your application runs on (default: 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
