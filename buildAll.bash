#!/bin/bash

echo "Starting to build project"
mvn clean compile assembly:single -DskipTests
echo "Copying database to target folder"
cp cache.db target/cache.db
cd target/
echo "Starting Bot"
java -jar lotus-webshop-bot-docker-jar-with-dependencies.jar
cd ..