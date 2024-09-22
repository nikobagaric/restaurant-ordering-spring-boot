# Use a base image with Java 17
FROM openjdk:22-jdk-slim

# Add a volume to be able to access the logs.
VOLUME /tmp

# Add the application's jar to the container
ARG JAR_FILE=build/libs/RestaurantOrdering-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
