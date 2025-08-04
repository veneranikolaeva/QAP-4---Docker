# Use OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Arguments to pass the JAR filename
ARG JAR_FILE=target/golfapi-0.0.1-SNAPSHOT.jar

# Copy the built JAR into the container
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]