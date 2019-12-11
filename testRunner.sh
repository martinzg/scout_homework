#!/bin/bash
echo "1) chrome = 1"
echo "2) firefox = 2"
read -p "Choose Browser: " number
if [ "$number" == "1" ]; then browser="chrome"
elif [ "$number" == "2" ]; then browser="firefox"
fi
echo "Browser chosen: " $browser
mvn clean test -Dbrowser=$browser
mvn allure:report
cp -avr allure-report/history allure-results