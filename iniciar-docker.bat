@echo off
cd /d %~dp0
echo Deteniendo contenedores anteriores...
docker compose down --remove-orphans
echo Construyendo y levantando el proyecto...
docker compose up --build
pause
