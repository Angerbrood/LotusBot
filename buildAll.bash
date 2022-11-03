#!/bin/bash

echo "Starting to build project"
mvn clean compile assembly:single -DskipTests
echo "Coping database from Anton"
scp -P 65410 angerbrood@angerbrood.synology.me:~/Project/LotusBot/cache.db /d/Projects/LotusBot/
cp cache.db target/cache.db
cd target/
echo "Starting Bot"
java -jar lotus-webshop-bot-docker-jar-with-dependencies.jar
cd ..
echo "Copying database to Anton"
scp -P 65410 cache.db angerbrood@angerbrood.synology.me:~/Project/LotusBot/