#!/bin/sh

git pull
./mvnw clean package
java -jar target/asteroids-internal-api-1.0.0.jar
