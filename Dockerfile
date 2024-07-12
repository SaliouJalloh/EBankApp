FROM openjdk:17-alpine
LABEL authors="saliou"
LABEL maintainer="ebanking.net"
LABEL version="1.0"
LABEL description="My Spring Boot API"
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ebanking-backend.jar
EXPOSE 8085
ENTRYPOINT [ "java", "-jar", "ebanking-backend.jar" ]

# docker build -t testapp_img .
# docker run -d -p 8085:8085 --name=testapp_con testapp_img
# docker inspect ID container | grep IPAddress (172.17.0.2)