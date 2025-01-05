@echo off
setlocal

:: Get script and project directories
set SCRIPT_DIR=%~dp0
set PROJECT_ROOT=%SCRIPT_DIR%..

:: Check if program name is provided
if "%~1"=="" (
    echo Usage: vns ^<program^>
    exit /b 1
)

:: Set up file paths
set PROGRAM=%~1
if not "%PROGRAM:~-4%"==".vmc" set PROGRAM=%PROGRAM%.vmc

:: Check if compiled file exists
if not exist "%PROGRAM%" (
    echo Error: Compiled file not found - %PROGRAM%
    exit /b 1
)

:: Run virtual machine
java -cp "%PROJECT_ROOT%\bin" VirtualMachine "%PROGRAM%"

:: Check for runtime errors
if errorlevel 1 (
    echo Program execution failed!
    exit /b 1
)

endlocal
