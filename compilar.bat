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
echo   Compilando proyecto completo y generando archivos .jar
echo ===============================================================
echo.
call "%MAVEN_CMD%" clean package -DskipTests
if errorlevel 1 (
  echo.
  echo ERROR: La compilacion fallo. Revisa la consola.
  pause
  exit /b 1
)
echo.
echo JARs generados correctamente en cada carpeta target.
pause