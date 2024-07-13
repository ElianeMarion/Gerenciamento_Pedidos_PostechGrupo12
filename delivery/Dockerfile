FROM openjdk:17-alpine

ADD target/delivery-1.0.0.jar delivery.jar

ENTRYPOINT [ "java", "-jar",  "/delivery.jar"]

EXPOSE 8084