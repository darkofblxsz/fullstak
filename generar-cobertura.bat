@echo off
setlocal
set "MAVEN_CMD=mvn"
where mvn >nul 2>nul
if errorlevel 1 (
    if exist "%USERPROFILE%\Downloads\apache-maven-3.9.16-bin\apache-maven-3.9.16\bin\mvn.cmd" (
        set "MAVEN_CMD=%USERPROFILE%\Downloads\apache-maven-3.9.16-bin\apache-maven-3.9.16\bin\mvn.cmd"
    ) else (
        echo ERROR: Maven no esta configurado en el PATH.
        echo Instala Maven o ejecuta manualmente con la ruta completa a mvn.cmd.
        pause
        exit /b 1
    )
)

echo ===============================================================
echo   Generando reporte de cobertura con JaCoCo
echo ===============================================================
echo.
call "%MAVEN_CMD%" clean verify
if errorlevel 1 (
  echo.
  echo ERROR: No se pudo generar la cobertura.
  pause
  exit /b 1
)
echo.
echo Revisa los reportes en cada modulo:
echo target/site/jacoco/index.html
pause