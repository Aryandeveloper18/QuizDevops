# Dockerfile for QuizApp
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/QuizApp-0.0.1-SNAPSHOT.jar QuizApp.jar

ENTRYPOINT ["java", "-jar", "QuizApp.jar"]

EXPOSE 8080