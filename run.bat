@echo off

REM Compile Java files
javac *.java

REM Start the RMI registry with -Djava.security.manager=null
start rmiregistry -J-Djava.security.manager=null

REM Sleep for a moment to allow the registry to start
timeout /t 2

REM Run the Main class
java -cp . Main
