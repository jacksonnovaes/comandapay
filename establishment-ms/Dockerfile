    FROM eclipse-temurin:21-alpine AS build

COPY . .

RUN ./mvnw clean install -DskipTests

ENTRYPOINT [ "java", "-jar", "target/establishment-ms-0.0.1-SNAPSHOT.jar" ]


