# Etapa de construção
FROM maven:3.8-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN rm -f /app/target/*.jar
RUN mvn clean install -DskipTests

# Etapa final
FROM eclipse-temurin:21.0.1_12-jdk-jammy
WORKDIR /app
ENV TZ=America/Sao_Paulo

WORKDIR /establishment-ms
ENV TZ=America/Sao_Paulo
RUN rm -f /app/target/*.jar

COPY --from=build /app/target/*.jar .
COPY certs /app/certs
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "establishment-ms-0.0.1-SNAPSHOT.jar"]
