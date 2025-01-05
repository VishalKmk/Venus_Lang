@echo off
setlocal
set SCRIPT_DIR=%~dp0
set PROJECT_ROOT=%SCRIPT_DIR%..
java -cp "%PROJECT_ROOT%\bin" Main %*
endlocal
