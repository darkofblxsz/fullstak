# Documentación de Endpoints del Sistema

Este documento describe la configuración de puertos y los principales endpoints REST expuestos por cada microservicio. Las peticiones externas deben ser dirigidas a través del **API Gateway** (puerto `8080`), el cual redirige a los servicios internos correspondientes.

---

## Índice de Puertos y Enrutamiento

| Microservicio | Puerto Directo | Prefijo Gateway | Descripción |
| :--- | :---: | :--- | :--- |
| **API Gateway** | `8080` | `/` | Puerta de enlace única para el sistema. |
| **Eureka Server** | `8761` | `/eureka/**` | Dashboard de administración y registro. |
| **ms-pedido** | `8081` | `/api/pedidos/**` | Gestión de órdenes de compra. |
| **ms-pago** | `8082` | `/api/pagos/**` | Procesamiento de pagos. |
| **ms-stock** | `8083` | `/api/stock/**` | Control de inventario físico. |
| **ms-proveedor** | `8084` | `/api/proveedores/**` | Gestión de proveedores externos. |
| **ms-producto** | `8086` | `/api/productos/**` | Catálogo de productos. |
| **ms-envio** | `8088` | `/api/envios/**` | Despacho y seguimiento de paquetes. |
| **ms-cliente** | `8089` | `/api/clientes/**` | Administración de clientes registrados. |
| **ms-categoria** | `8090` | `/api/categorias/**` | Categorización del catálogo. |
| **ms-carrito** | `8091` | `/api/carrito/**` | Canasta de compras temporal del cliente. |
| **ms-boleta** | `8092` | `/api/boletas/**` | Generación de documentos tributarios. |

---

## Detalle de Endpoints (Rutas a través del API Gateway)

### 1. Clientes (`ms-cliente` -> `/api/clientes`)
* `GET /api/clientes` - Obtener lista de clientes activos.
* `GET /api/clientes/{id}` - Obtener datos de un cliente específico.
* `POST /api/clientes` - Registrar un nuevo cliente.
* `PUT /api/clientes/{id}` - Actualizar información de cliente.
* `DELETE /api/clientes/{id}` - Desactivar cliente (borrado lógico).

### 2. Categorías (`ms-categoria` -> `/api/categorias`)
* `GET /api/categorias` - Listar categorías del catálogo.
* `POST /api/categorias` - Crear nueva categoría.
* `PUT /api/categorias/{id}` - Modificar una categoría.
* `DELETE /api/categorias/{id}` - Eliminar categoría.

### 3. Proveedores (`ms-proveedor` -> `/api/proveedores`)
* `GET /api/proveedores` - Listar proveedores.
* `POST /api/proveedores` - Crear proveedor.
* `GET /api/proveedores/{id}` - Detalle de un proveedor.

### 4. Productos (`ms-producto` -> `/api/productos`)
* `GET /api/productos` - Listar todos los productos.
* `GET /api/productos/{id}` - Detalle de producto.
* `POST /api/productos` - Crear producto.
* `PUT /api/productos/{id}` - Modificar producto.

### 5. Stock (`ms-stock` -> `/api/stock`)
* `GET /api/stock/{productoId}` - Consultar stock disponible de un producto.
* `POST /api/stock/update` - Modificar/Restar stock ante una compra.
* `POST /api/stock/replenish` - Aumentar stock de un producto (abastecimiento).

### 6. Carrito (`ms-carrito` -> `/api/carrito`)
* `GET /api/carrito/cliente/{clienteId}` - Obtener el carrito actual del cliente.
* `POST /api/carrito/add` - Añadir ítem al carrito.
* `DELETE /api/carrito/remove/{detalleId}` - Quitar ítem del carrito.
* `POST /api/carrito/clear/{clienteId}` - Vaciar el carrito.

### 7. Pedidos (`ms-pedido` -> `/api/pedidos`)
* `GET /api/pedidos` - Listar pedidos generales (administración).
* `GET /api/pedidos/cliente/{clienteId}` - Listar historial del cliente.
* `POST /api/pedidos` - Crear orden de pedido a partir del carrito.
* `PUT /api/pedidos/{id}/estado` - Cambiar estado (PAGADO, ENVIADO, etc.).

### 8. Pagos (`ms-pago` -> `/api/pagos`)
* `POST /api/pagos` - Procesar una transacción de pago para un pedido.
* `GET /api/pagos/pedido/{pedidoId}` - Consultar pago asociado a un pedido.
* `GET /api/pagos/transaccion/{transaccionId}` - Verificar estado de transacción.

### 9. Boletas (`ms-boleta` -> `/api/boletas`)
* `GET /api/boletas/{id}` - Buscar boleta por ID.
* `GET /api/boletas/pedido/{pedidoId}` - Buscar boleta de un pedido específico.
* `POST /api/boletas/generate` - Emitir boleta electrónica.

### 10. Envíos (`ms-envio` -> `/api/envios`)
* `GET /api/envios/tracking/{numeroSeguimiento}` - Obtener estado actual del despacho.
* `POST /api/envios` - Crear orden de envío.
* `PUT /api/envios/{id}/estado` - Actualizar tracking (EN_RUTA, ENTREGADO).

---

## Documentación de API Interactiva (Swagger/OpenAPI)

Tras la migración, la documentación OpenAPI interactiva estará disponible de manera individual en cada microservicio de negocio bajo la siguiente ruta local:

* **Swagger UI**: `http://localhost:[PUERTO_DIRECTO]/swagger-ui.html`
* **JSON API Docs**: `http://localhost:[PUERTO_DIRECTO]/v3/api-docs`

*(Ejemplo para ms-producto: `http://localhost:8086/swagger-ui.html`)*
