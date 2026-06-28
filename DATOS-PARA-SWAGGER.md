# Datos listos para probar en Swagger

Usar primero los servicios base y luego los servicios que dependen de IDs.

## Orden recomendado

1. Crear cliente.
2. Crear categoría.
3. Crear proveedor.
4. Crear producto.
5. Crear stock para el producto.
6. Probar carrito, pedido, pago, envío y boleta.

## 1. Crear cliente

POST `http://localhost:8080/api/clientes`

```json
{
  "nombre": "Juan",
  "apellido": "Perez",
  "correo": "juan.perez@mail.com",
  "telefono": "912345678"
}
```

## 2. Crear categoría

POST `http://localhost:8080/api/categorias`

```json
{
  "nombre": "Bebidas",
  "descripcion": "Bebidas y productos liquidos"
}
```

## 3. Crear proveedor

POST `http://localhost:8080/api/proveedores`

```json
{
  "nombre": "Proveedor Uno",
  "correo": "proveedor@mail.com",
  "telefono": "912345679"
}
```

## 4. Crear producto

POST `http://localhost:8080/api/productos`

```json
{
  "nombre": "Bebida Cola",
  "descripcion": "Bebida de fantasia 1.5 litros",
  "precio": 1590,
  "stock": 20,
  "categoria": "Bebidas",
  "marca": "Marca Uno",
  "categoriaId": 1,
  "proveedorId": 1,
  "codigoBarras": "7800000000012"
}
```

## 5. Crear stock

POST `http://localhost:8080/api/stock`

```json
{
  "productoId": 1,
  "cantidad": 20,
  "stockMinimo": 5,
  "stockMaximo": 100,
  "ubicacion": "Bodega A"
}
```

## 6. Agregar producto al carrito

POST `http://localhost:8080/api/carrito/1`

```json
{
  "productoId": 1,
  "cantidad": 2
}
```

## 7. Crear pedido

POST `http://localhost:8080/api/pedidos`

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

## 8. Crear pago

POST `http://localhost:8080/api/pagos`

```json
{
  "pedidoId": 1,
  "metodoPago": "Debito",
  "monto": 3180,
  "estado": "PAGADO"
}
```

## 9. Crear envío

POST `http://localhost:8080/api/envios`

```json
{
  "pedidoId": 1,
  "clienteId": 1,
  "direccion": "Av. Siempre Viva 123"
}
```

## 10. Crear boleta

POST `http://localhost:8080/api/boletas`

```json
{
  "pedidoId": 1,
  "clienteId": 1,
  "total": 3180,
  "fecha": "2026-06-26",
  "metodoPago": "Debito",
  "estado": "EMITIDA",
  "iva": 604.2,
  "totalFinal": 3784.2
}
```

## Recomendación

Si aparece un error por ID inexistente, primero ejecutar los POST anteriores en el orden indicado. Si se quiere limpiar todo y partir desde cero con Docker, ejecutar `docker-reset.bat`.
