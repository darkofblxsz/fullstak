@echo off
cd /d "%~dp0"

echo ===============================================================
echo   Generando reporte de cobertura con JaCoCo
echo ===============================================================
echo.

call mvn.cmd clean verify

if %ERRORLEVEL% NEQ 0 (
  echo.
  echo ERROR: No se pudo generar la cobertura.
  pause
  exit /b 1
)

echo.
echo Cobertura generada correctamente.
echo Revisa los reportes en cada modulo:
echo target\site\jacoco\index.html
pause
