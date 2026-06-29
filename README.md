# FullStack Supermercado - Arquitectura de Microservicios

## Integrantes
- Matias Maldonado
- Diego Clavero
- Victoria Gonzalez

## Descripción
Proyecto desarrollado con Spring Boot basado en arquitectura de microservicios para la gestión de un supermercado. La solución utiliza Docker para el despliegue, Eureka Server para el descubrimiento de servicios y API Gateway como punto de acceso.

## Tecnologías
- Java 21
- Spring Boot
- Spring Cloud
- Eureka Server
- API Gateway
- MySQL
- Docker & Docker Compose
- Maven
- OpenAPI / Swagger

## Requisitos
- Docker Desktop
- Docker Compose
- Git
- Java 21 (solo para desarrollo)

## Ejecución

Levantar el proyecto:

```bash
docker compose up --build
```

Detener el proyecto:

```bash
docker compose down -v
```

## URLs

### Eureka
http://localhost:8761

### API Gateway
http://localhost:8080

### Swagger

| Microservicio | URL |
|---|---|
| Producto | http://localhost:8086/swagger-ui.html |
| Categoría | http://localhost:8090/swagger-ui.html |
| Proveedor | http://localhost:8084/swagger-ui.html |
| Stock | http://localhost:8083/swagger-ui.html |
| Cliente | http://localhost:8089/swagger-ui.html |
| Carrito | http://localhost:8091/swagger-ui.html |
| Pedido | http://localhost:8081/swagger-ui.html |
| Pago | http://localhost:8082/swagger-ui.html |
| Envío | http://localhost:8088/swagger-ui.html |
| Boleta | http://localhost:8092/swagger-ui.html |

## Estructura
- Eureka Server
- API Gateway
- 10 Microservicios
- Base de datos MySQL
- Docker Compose

## Observaciones
1. Iniciar Docker Desktop antes de ejecutar el proyecto.
2. Verificar que el puerto 3306 esté disponible.
3. Confirmar en Eureka que todos los servicios estén en estado **UP** antes de realizar pruebas.


## Video Grupal
- https://drive.google.com/drive/folders/1e6n143Hvx1liH1MWeCHsL5HJWd6CCL69?usp=sharing
- En este link se encuentra el video grupal con un word y  archivo txt con los subtitulos  
