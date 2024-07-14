FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn package -Dmaven.test.skip
FROM openjdk:17-jdk-slim
EXPOSE 8081
COPY --from=build /app/target/*.jar /app/appcustomer.jar
ENTRYPOINT ["java", "-jar", "/app/appcustomer.jar"]

#FROM openjdk:17
#WORKDIR /app
#COPY target/*.jar application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]