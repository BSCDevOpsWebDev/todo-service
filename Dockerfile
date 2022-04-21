# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/todo-service-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java", "-Dspring.HOSTNAME=${HOSTNAME}", "-Dspring.EUREKAPORT=${EUREKAPORT}", "-Dspring.CONFIGPORT=${CONFIGPORT}", "-Dspring.CONFIGURL=${CONFIG_URL}", "-Dspring.CONFIGUSER=${CONFIGUSER}", "-Dspring.CONFIGHTTP=${CONFIGHTTP}", "-jar", "app.jar"]
