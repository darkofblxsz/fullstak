@echo off
cd /d "%~dp0"

echo ===============================================================
echo   Compilando proyecto completo y generando archivos .jar
echo ===============================================================
echo.

call mvn.cmd clean package -DskipTests

if %ERRORLEVEL% NEQ 0 (
  echo.
  echo ERROR: La compilacion fallo. Revisa la consola.
  pause
  exit /b 1
)

echo.
echo JARs generados correctamente en cada carpeta target.
pause
