# Proyecto FullStack I - Ecosistema de Microservicios Minimarket

Proyecto semestral desarrollado para la asignatura **Desarrollo FullStack I **. El sistema está organizado como una arquitectura de microservicios con Eureka Server, API Gateway y servicios de negocio independientes.

## Integrantes

- Matias Maldonado
- Victoria Gonzalez
- Diego clavero


## Arquitectura general

El proyecto usa un proyecto padre Maven multi-módulo. Cada microservicio mantiene una estructura basada en el patrón CSR:

- `controller`: recibe las peticiones REST.
- `service`: concentra la lógica de negocio.
- `repository`: acceso a datos con Spring Data JPA.
- `model` / `modelo`: entidades JPA.
- `dto`: objetos de entrada y salida cuando corresponde.

## Módulos implementados

| Módulo | Puerto | Base de datos | Descripción |
|---|---:|---|---|
| eureka-server | 8761 | No aplica | Registro y descubrimiento de servicios |
| api-gateway | 8080 | No aplica | Entrada centralizada hacia los microservicios |
| ms-pedido | 8081 | db_pedidos | Gestión de pedidos |
| ms-pago | 8082 | db_pagos | Gestión de pagos |
| ms-stock | 8083 | db_stock | Gestión de inventario |
| ms-proveedor | 8084 | db_proveedores | Gestión de proveedores |
| ms-producto | 8086 | db_productos | Gestión de productos |
| ms-envio | 8088 | db_envios | Gestión de envíos |
| ms-cliente | 8089 | db_clientes | Gestión de clientes |
| ms-categoria | 8090 | db_categorias | Gestión de categorías |
| ms-carrito | 8091 | db_carrito | Gestión de carrito de compras |
| ms-boleta | 8092 | db_boletas | Gestión de boletas |

## Requisitos previos

- JDK 21.
- Maven o Maven Wrapper incluido en el proyecto.
- MySQL local activo.
- Bases de datos creadas previamente.
- Postman o navegador para probar endpoints.

## Bases de datos necesarias

Crear estas bases de datos en MySQL antes de levantar los microservicios:

```sql
CREATE DATABASE db_clientes;
CREATE DATABASE db_categorias;
CREATE DATABASE db_productos;
CREATE DATABASE db_stock;
CREATE DATABASE db_proveedores;
CREATE DATABASE db_pedidos;
CREATE DATABASE db_pagos;
CREATE DATABASE db_envios;
CREATE DATABASE db_carrito;
CREATE DATABASE db_boletas;
```

Por defecto se usan estas credenciales:

```text
DB_USERNAME=rootmatty
DB_PASSWORD=hola123
```

También se pueden cambiar como variables de entorno:

```bat
set DB_USERNAME=root
set DB_PASSWORD=
```

## Compilar y generar los JAR

Desde la raíz del proyecto:

```bat
mvn clean package -DskipTests
```

También se puede usar:

```bat
compilar.bat
```

## Ejecutar pruebas unitarias

Desde la raíz del proyecto:

```bat
mvn clean test
```

O usando el archivo preparado:

```bat
probar.bat
```

Los reportes se generan en:

```text
<modulo>/target/surefire-reports/
```

## Reporte de cobertura

Para generar cobertura con JaCoCo:

```bat
mvn clean verify
```

O usando:

```bat
generar-cobertura.bat
```

Luego revisar:

```text
<modulo>/target/site/jacoco/index.html
```

## Puesta en marcha sin Docker

El docente solicitó levantar el sistema sin Docker desde archivos `.jar`, en este orden:

1. Eureka Server.
2. Microservicios de negocio.
3. API Gateway.

Pasos:

```bat
compilar.bat
iniciar.bat
```

El archivo `iniciar.bat` levanta automáticamente el ecosistema en el orden indicado.

## URLs principales

| Componente | URL |
|---|---|
| Eureka Dashboard | http://localhost:8761 |
| API Gateway | http://localhost:8080 |

## Rutas principales del Gateway

| Servicio | Ruta Gateway |
|---|---|
| Clientes | http://localhost:8080/api/clientes |
| Categorías | http://localhost:8080/api/categorias |
| Proveedores | http://localhost:8080/api/proveedores |
| Productos | http://localhost:8080/api/productos |
| Stock | http://localhost:8080/api/stock |
| Carrito | http://localhost:8080/api/carrito |
| Pedidos | http://localhost:8080/api/pedidos |
| Pagos | http://localhost:8080/api/pagos |
| Boletas | http://localhost:8080/api/boletas |
| Envíos | http://localhost:8080/api/envios |

## Swagger / OpenAPI

Cada microservicio de negocio tiene dependencia `springdoc-openapi-starter-webmvc-ui` y una clase `SwaggerConfig` para personalizar la documentacion.

Formato de acceso:

```text
http://localhost:<PUERTO>/doc/swagger-ui/index.html
```

Ejemplos:

| Servicio | Swagger |
|---|---|
| ms-cliente | http://localhost:8089/doc/swagger-ui/index.html |
| ms-categoria | http://localhost:8090/doc/swagger-ui/index.html |
| ms-producto | http://localhost:8086/doc/swagger-ui/index.html |
| ms-stock | http://localhost:8083/doc/swagger-ui/index.html |
| ms-pedido | http://localhost:8081/doc/swagger-ui/index.html |

## Configuración YAML

Se agregaron archivos `application.yml` para los módulos principales. Estos archivos documentan de forma clara:

- nombre del servicio;
- puerto;
- conexión a MySQL;
- conexión a Eureka;
- Swagger/OpenAPI;
- configuración JPA.

El Gateway usa `application.yaml` con rutas hacia cada microservicio mediante `lb://nombre-servicio`.

## Evidencia para la defensa técnica

Durante la defensa se recomienda mostrar:

1. `mvn clean test` ejecutando pruebas.
2. Eureka en `http://localhost:8761` mostrando servicios registrados.
3. Gateway respondiendo en `http://localhost:8080`.
4. Swagger de un microservicio.
5. Un endpoint probado desde Postman.
6. Explicación de un test unitario con Given/When/Then y Mockito.

## Comandos útiles

Ejecutar solo las pruebas de un microservicio:

```bat
mvn -pl ms-cliente test
```

Ejecutar un test específico:

```bat
mvn -pl ms-cliente -Dtest=ClienteServiceImplTest test
```

Compilar solo un módulo:

```bat
mvn -pl ms-stock clean package
```

## Notas importantes

- Docker no es obligatorio para esta entrega, pero puede quedar como mejora futura.
- No subir cambios después de la fecha límite indicada por el docente.
- Antes de la defensa, ejecutar pruebas y verificar que todos los servicios se registren en Eureka.

## Swagger personalizado segun guia practica

Se implemento Swagger siguiendo la guia practica entregada por el docente. La guia indica agregar la dependencia `springdoc-openapi-starter-webmvc-ui`, habilitar `springdoc.api-docs.enabled`, habilitar `springdoc.swagger-ui.enabled` y configurar una ruta personalizada para Swagger UI.

Ruta recomendada por microservicio:

```text
http://localhost:<PUERTO>/doc/swagger-ui/index.html
```

Ejemplos:

| Servicio | Swagger UI |
|---|---|
| ms-pedido | http://localhost:8081/doc/swagger-ui/index.html |
| ms-pago | http://localhost:8082/doc/swagger-ui/index.html |
| ms-stock | http://localhost:8083/doc/swagger-ui/index.html |
| ms-proveedor | http://localhost:8084/doc/swagger-ui/index.html |
| ms-producto | http://localhost:8086/doc/swagger-ui/index.html |
| ms-envio | http://localhost:8088/doc/swagger-ui/index.html |
| ms-cliente | http://localhost:8089/doc/swagger-ui/index.html |
| ms-categoria | http://localhost:8090/doc/swagger-ui/index.html |
| ms-carrito | http://localhost:8091/doc/swagger-ui/index.html |
| ms-boleta | http://localhost:8092/doc/swagger-ui/index.html |

Tambien se agregaron clases `SwaggerConfig` en los paquetes `config` de los microservicios y anotaciones `@Tag`, `@Operation`, `@ApiResponse` y `@ApiResponses` en los controladores principales.
