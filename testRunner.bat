@echo off
echo 1) chrome = 1
echo 2) firefox = 2
set /p number=Choose Browser: 
if %number% == 1 (
   set browser="chrome"
)
if  %number% == 2 (
   set browser="firefox"
)
echo Browser chosen: %browser% 
mvn clean test -Dbrowser=%browser% & mvn allure:report && xcopy /e/y/i allure-report\history allure-results\history & .allure\allure-2.8.1\bin\allure.bat open --host localhost