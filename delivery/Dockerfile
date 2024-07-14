FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn package
FROM openjdk:17-jdk-slim
EXPOSE 8084
COPY --from=build /app/target/*.jar /app/appdelivery.jar
ENTRYPOINT ["java", "-jar", "/app/appdelivery.jar"]

#FROM openjdk:17-alpine
#
#ADD target/delivery-1.0.0.jar delivery.jar
#
#ENTRYPOINT [ "java", "-jar",  "/delivery.jar"]
#
#EXPOSE 8084