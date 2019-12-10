@echo off
echo 1) chrome = 1
echo 2) firefox = 2
set /p number=Choose Browser: 
if %number% == "1" (
   set browser="chrome"
)
if  %number% == 2 (
   set browser="firefox"
)
mvn test -Dbrowser=%browser% & allure generate --clean & xcopy /e/y/i allure-report\history allure-results\history && allure serve --host localhost