# Stage 1: Build the application
FROM openjdk:17-jdk-slim as build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /home/app

# Copy the project files into the container
COPY . /home/app

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /home/app

# Copy the built JAR file from the previous stage
COPY --from=build /home/app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]