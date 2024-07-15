FROM maven:3.8-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Etapa final
FROM eclipse-temurin:21.0.1_12-jdk-jammy
WORKDIR /app
ENV TZ=America/Sao_Paulo
COPY --from=build /app/target/*.jar .
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "epay-auth-0.0.1-SNAPSHOT", "--spring.profiles.active=local"]
