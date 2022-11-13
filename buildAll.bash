#!/bin/bash

echo "Starting to build project"
mvn clean compile assembly:single -DskipTests
echo "Starting Bot"
java -jar lotus-webshop-bot-docker-jar-with-dependencies.jar
cd ..