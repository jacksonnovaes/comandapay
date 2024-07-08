FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pay-auth-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pay-auth-0.0.1-SNAPSHOT.jar"]