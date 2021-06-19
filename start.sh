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
export BOT_DB_USERNAME='postgres'
export BOT_DB_PASSWORD='postgres'

#START DEPLOY
docker-compose up --build -d