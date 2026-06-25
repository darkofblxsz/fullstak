@echo off
cd /d "%~dp0"

echo ===============================================================
echo   Ejecutando pruebas unitarias del proyecto completo
echo ===============================================================
echo.

call mvn.cmd clean test

if %ERRORLEVEL% NEQ 0 (
  echo.
  echo ERROR: Hay pruebas fallidas o errores de compilacion.
  echo Revisa los reportes en cada modulo target\surefire-reports
  pause
  exit /b 1
)

echo.
echo Pruebas finalizadas correctamente.
echo Reportes disponibles en cada modulo target\surefire-reports
pause
