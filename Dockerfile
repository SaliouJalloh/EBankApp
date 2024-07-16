#Generate Build
FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X


# Dockerize
FROM openjdk:17-alpine
COPY --from=build app/target/*.jar ebanking.jar
EXPOSE 8085
ENTRYPOINT [ "java", "-jar", "ebanking.jar" ]

# docker build -t testapp_img .
# docker run -d -p 8085:8085 --name=testapp_con testapp_img
# docker inspect ID container | grep IPAddress (172.17.0.2)

# Pull sur docker hub
#docker build -t liousa/demo:0.0.1-SNAPSHOT .

#docker login

#docker push liousa/demo:0.0.1-SNAPSHOT