# Flujo funcional de prueba - Supermercado

Orden recomendado para probar desde Swagger UI centralizado:

1. Abrir `http://localhost:8080/doc/swagger-ui.html`.
2. Crear categoria en `ms-categoria`:
```json
{
  "nombre": "Tecnologia",
  "descripcion": "Productos electronicos y computacion"
}
```
3. Crear proveedor en `ms-proveedor`:
```json
{
  "nombre": "Proveedor Lenovo Chile",
  "correo": "ventas@lenovo.cl",
  "telefono": "987654321"
}
```
4. Crear producto en `ms-producto`:
```json
{
  "nombre": "Notebook Lenovo",
  "descripcion": "Notebook para oficina",
  "precio": 450000,
  "stock": 10,
  "categoria": "Tecnologia",
  "marca": "Lenovo",
  "categoriaId": 1,
  "proveedorId": 1,
  "codigoBarras": "7800000000012"
}
```
5. Crear stock en `ms-stock`:
```json
{
  "productoId": 1,
  "cantidad": 10,
  "stockMinimo": 2,
  "stockMaximo": 50,
  "ubicacion": "Bodega A"
}
```
6. Crear cliente en `ms-cliente`:
```json
{
  "nombre": "Matias",
  "apellido": "Maldonado",
  "correo": "matias@mail.com",
  "telefono": "987654321"
}
```
7. Agregar producto al carrito en `ms-carrito` usando cliente 1:
```json
{
  "productoId": 1,
  "cantidad": 2
}
```
8. Crear pedido en `ms-pedido`:
```json
{
  "clienteId": 1,
  "items": [
    {
      "productoId": 1,
      "cantidad": 2
    }
  ]
}
```
9. Registrar pago en `ms-pago`:
```json
{
  "pedidoId": 1,
  "metodoPago": "DEBITO",
  "monto": 2000,
  "estado": "PAGADO"
}
```
10. Crear envio en `ms-envio`:
```json
{
  "pedidoId": 1,
  "clienteId": 1,
  "direccion": "Av. Siempre Viva 123"
}
```
11. Crear boleta en `ms-boleta`:
```json
{
  "pedidoId": 1,
  "clienteId": 1,
  "total": 2000,
  "fecha": "2026-06-21",
  "metodoPago": "DEBITO",
  "estado": "EMITIDA"
}
```

Evidencia esperada: todos los servicios registrados en Eureka, Swagger publico y endpoints respondiendo 200 o 201.
