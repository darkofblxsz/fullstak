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
echo   Ejecutando pruebas unitarias del proyecto completo
echo ===============================================================
echo.
call "%MAVEN_CMD%" clean test
if errorlevel 1 (
  echo.
  echo ERROR: Hay pruebas fallidas o errores de compilacion.
  echo Revisa los reportes en */target/surefire-reports/
  pause
  exit /b 1
)
echo.
echo Pruebas finalizadas correctamente.
echo Reportes disponibles en */target/surefire-reports/
pause