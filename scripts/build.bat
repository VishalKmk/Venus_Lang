@echo off
setlocal EnableDelayedExpansion

:: Set project directories
set SCRIPT_DIR=%~dp0
set PROJECT_ROOT=%SCRIPT_DIR%..
cd %PROJECT_ROOT%

:: Check Java version
java -version >nul 2>&1
if errorlevel 1 (
    echo [31mError: Java not found in PATH[0m
    exit /b 1
)

echo [36mStarting Venus Compiler build...
echo Timestamp: %date% %time%[0m

:: Clean and create bin directory
if exist bin (
    echo Cleaning bin directory...
    rd /s /q bin
)
mkdir bin

:: Compile all Java files
echo Compiling source files...
javac -d bin src\*.java src\exceptions\*.java 2>build_errors.txt
if errorlevel 1 (
    echo [31mBuild failed! See build_errors.txt for details[0m
    type build_errors.txt
    del build_errors.txt
    exit /b 1
)

:: Success message
echo [32mBuild successful![0m
echo Output directory: %PROJECT_ROOT%\bin

endlocal