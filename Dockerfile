# Stage 1: Build the JAR
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Package the Spring Boot application
RUN mvn clean package -DskipTests


# Stage 1: Build the JAR
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/application.properties application.properties

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

