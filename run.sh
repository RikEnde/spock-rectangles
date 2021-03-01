#!/bin/bash

mvn clean package
java -jar target/rectangles-0.0.1-SNAPSHOT.jar
