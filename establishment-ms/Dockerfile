# Etapa de construção
FROM maven:3.8-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Etapa final
FROM eclipse-temurin:21.0.1_12-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar .
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "establishment-ms-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]
