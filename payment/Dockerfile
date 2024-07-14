FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn package -Dmaven.test.skip
FROM openjdk:17-jdk-slim
EXPOSE 8086
COPY --from=build /app/target/*.jar /app/apppayment.jar
ENTRYPOINT ["java", "-jar", "/app/apppayment.jar"]

#FROM ubuntu:latest
#LABEL authors="elian"
#
#ENTRYPOINT ["top", "-b"]