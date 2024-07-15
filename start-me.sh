#!/bin/sh
docker compose down -v

mvn clean install -DskipTests

docker build -t establishment-ms -f establishment-ms/Dockerfile ./establishment-ms

docker build -t pay-auth -f pay-auth/Dockerfile ./pay-auth

docker compose up --build --force-recreate --remove-orphans