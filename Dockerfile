FROM gradle:7.5.1-jdk17 AS build
FROM openjdk:17

EXPOSE 8000

COPY build/libs/ll-o-m-0.1.jar /ll-o-m-0.1.jar

RUN groupadd -r myuser && \
    useradd --no-log-init -r -g myuser myuser

USER myuser

ENTRYPOINT ["java", "-jar", "/ll-o-m-0.1.jar"]