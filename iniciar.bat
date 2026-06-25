@echo off
cd /d "%~dp0"

echo =====================================================================
echo    Iniciando Ecosistema de Microservicios Supermercado
echo =====================================================================
echo.

echo [1/3] Levantando Eureka Server...
start "Eureka Server" cmd /k java -jar eureka-server\target\eureka-server-1.0.0-SNAPSHOT.jar
echo Esperando 12 segundos...
timeout /t 12 /nobreak
echo.

echo [2/3] Levantando Microservicios...
start "MS Pedido" cmd /k java -jar ms-pedido\target\ms-pedido-1.0.0-SNAPSHOT.jar
start "MS Pago" cmd /k java -jar ms-pago\target\ms-pago-1.0.0-SNAPSHOT.jar
start "MS Stock" cmd /k java -jar ms-stock\target\ms-stock-1.0.0-SNAPSHOT.jar
start "MS Proveedor" cmd /k java -jar ms-proveedor\target\ms-proveedor-1.0.0-SNAPSHOT.jar
start "MS Producto" cmd /k java -jar ms-producto\target\ms-producto-1.0.0-SNAPSHOT.jar
start "MS Envio" cmd /k java -jar ms-envio\target\ms-envio-1.0.0-SNAPSHOT.jar
start "MS Cliente" cmd /k java -jar ms-cliente\target\ms-cliente-1.0.0-SNAPSHOT.jar
start "MS Categoria" cmd /k java -jar ms-categoria\target\ms-categoria-1.0.0-SNAPSHOT.jar
start "MS Carrito" cmd /k java -jar ms-carrito\target\ms-carrito-1.0.0-SNAPSHOT.jar
start "MS Boleta" cmd /k java -jar ms-boleta\target\ms-boleta-1.0.0-SNAPSHOT.jar

echo Esperando 15 segundos...
timeout /t 15 /nobreak
echo.

echo [3/3] Levantando API Gateway...
start "API Gateway" cmd /k java -jar api-gateway\target\api-gateway-1.0.0-SNAPSHOT.jar
echo.

echo =====================================================================
echo    Ecosistema iniciado correctamente
echo    Eureka Server Dashboard: http://localhost:8761
echo    API Gateway Base URL: http://localhost:8080
echo    Swagger producto: http://localhost:8086/doc/swagger-ui/index.html
echo    Swagger stock: http://localhost:8083/doc/swagger-ui/index.html
echo    Swagger categoria: http://localhost:8090/doc/swagger-ui/index.html
echo =====================================================================
echo.
pause
