# Use a JDK image as base image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application jar file to the container
COPY target/loan_management-0.0.1-SNAPSHOT.jar /app/loan.jar

# Expose the port used by the application
EXPOSE 8005

# Set the command to run the application when the container starts
CMD ["java", "-jar", "loan.jar"]