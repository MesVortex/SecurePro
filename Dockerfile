# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR
COPY target/*.jar /app/secure-pro.jar

# Expose the application port
EXPOSE 8081

# Start the application
ENTRYPOINT ["java", "-jar", "/app/secure-pro.jar"]
