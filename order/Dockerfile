FROM openjdk:17-alpine

ADD target/order-1.0.0.jar order.jar

ENTRYPOINT [ "java", "-jar",  "/order.jar"]

EXPOSE 8083