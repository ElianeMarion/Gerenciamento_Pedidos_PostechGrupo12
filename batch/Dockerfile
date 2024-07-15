FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn package -Dmaven.test.skip
FROM openjdk:17-jdk-slim
EXPOSE 8087
ENV SPRING_PROFILES_ACTIVE=docker
COPY --from=build /app/target/*.jar /app/appbatch.jar
ENTRYPOINT ["java", "-jar", "/app/appbatch.jar"]

#FROM amazoncorretto:17-alpine-jdk
#
#WORKDIR /app
#
#RUN addgroup --system javauser \
#    && adduser -S -s /usr/sbin/nologin -G javauser javauser
#
#COPY target/batch.jar /app/batch.jar
#
#RUN chown -R javauser:javauser .
#
#USER javauser
#
#ENV SPRING_PROFILES_ACTIVE=docker
#
#ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "batch.jar"]
