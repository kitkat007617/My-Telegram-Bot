#!/bin/bash

# Pull new changes
git pull

#Prepare jar
mvnw clear
mvnw package

#Ensure, that docker-compose stop
docker-compose stop

#ADD ENVIRONMENT VARIABLES
export BOT_NAME=$1
export BOT_TOKEN=$2

#START DEPLOY
docker-compose up --build -d