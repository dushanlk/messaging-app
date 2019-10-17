#!/usr/bin/env bash

# Authored by dushan.p@viewqwest.com on 15/10/19.
# http://dushan.lk

printf "\n===================================\nYour Java version\n===================================\n"

printf `java -version`

printf "\n===================================\nCompiling messaging app\n===================================\n"

if [[ $1 == 'skip-tests' ]]
then
    mvn clean install -DskipTests
else
    mvn clean install
fi

printf "\n===================================\nRunning messaging app\n===================================\n"

java -jar target/messaging-app-1.0-SNAPSHOT.jar

printf  "\n===================================\nStopped messaging app\n===================================\n"
