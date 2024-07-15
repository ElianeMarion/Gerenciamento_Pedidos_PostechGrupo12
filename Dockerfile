FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

RUN addgroup --system javauser \
    && adduser -S -s /usr/sbin/nologin -G javauser javauser

COPY target/batch.jar /app/batch.jar

RUN chown -R javauser:javauser .

USER javauser

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "batch.jar"]
