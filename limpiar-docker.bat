@echo off
cd /d %~dp0
echo ADVERTENCIA: esto borrara contenedores y datos de MySQL del proyecto.
pause
docker compose down -v --remove-orphans
pause
