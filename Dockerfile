FROM openjdk:17-jdk-alpine
LABEL authors="saliou"
LABEL maintainer="ebanking.net"
LABEL version="1.0"
LABEL description="My Spring Boot API"
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ebanking-backend.jar
EXPOSE 8085
ENTRYPOINT [ "java", "-jar", "ebanking-backend.jar" ]