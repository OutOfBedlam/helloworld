#!/bin/bash
sbt clean universal:packageZipTarball
docker-compose rm -f
docker-compose build
docker-compose up
