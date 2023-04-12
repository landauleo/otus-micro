FROM gradle:7.5.1-jdk17-alpine AS build
FROM openjdk:17

EXPOSE 8080

COPY build/libs/ll-o-m-0.1.jar /ll-o-m-0.1.jar

ENTRYPOINT ["java","-jar","/ll-o-m-0.1.jar"]