#!/bin/bash

echo "Starting to build project"
mvn clean compile assembly:single -DskipTests
echo "Starting Bot"
cd target/
java -jar lotus-webshop-bot-docker-jar-with-dependencies.jar
cd ..