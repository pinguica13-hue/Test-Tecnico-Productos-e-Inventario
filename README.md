Sistema de Microservicios - Productos e Inventario
Este proyecto implementa un sistema de gestión de productos e inventario utilizando Spring Boot + JPA + MySQL/H2, siguiendo un enfoque de microservicios.

1. Microservicios
  a. MSProductos
   -Gestión de productos.
   -Endpoints para crear, consultar y listar productos.
   -Base de datos propia (productos_db).
  a. MSInventario
   -Gestión de inventario y compras.
   -Consulta y actualización del stock de productos.
   -Comunicación con MSProductos vía HTTP + API Key.
   -Base de datos propia (inventario_db).
   
2. Arquitectura
```mermaid
    flowchart LR
    A[Cliente / Frontend] -->|JSON API| B[MSProductos]
    A -->|JSON API| C[MSInventario]
    C -->|HTTP + API Key| B
    B -->|DB Productos| D[(MySQL / H2)]
    C -->|DB Inventario| E[(MySQL / H2)]

    -Comunicación entre microservicios por HTTP/JSON.
    -Autenticación básica con API Key.
    -Manejo de timeout y reintentos en el cliente REST.

3. Tecnologías
    -Java 17
    -Spring Boot 3.x
    -Spring Data JPA
    -MySQL / H2 (test)
    -Lombok
    -Swagger / OpenAPI
    -JUnit 5 + Mockito

4. Documentación API
Swagger habilitado en ambos servicios:
MSProductos → http://localhost:8080/swagger-ui.html
MSInventario → http://localhost:8081/swagger-ui.html

5. Endpoints principales
a. MSProductos
    POST /productos → Crear producto.
    GET /productos/{id} → Obtener producto por ID.
    GET /productos → Listar todos los productos.

b. MSInventario
    GET /inventario/{productoId} → Consultar stock de producto.
    PUT /inventario/{productoId} → Actualizar stock de producto.
    POST /compras → Realizar compra (verifica stock y descuenta inventario).

6. Seguridad (API Key)
La comunicación entre MSInventario → MSProductos utiliza autenticación con API Key.
Agregar en el application.yml de ms-inventario:
msproductos:
  url: http://localhost:8080
  apikey: my-secret-key

7.Testing
Se incluyen pruebas unitarias y de integración:
a. MSProductos
    Creación de productos.
    Consulta de productos.

b. MSInventario
    Gestión de inventario.
    Proceso de compra (incluye comunicación con MSProductos).
    Manejo de errores:
    Producto no encontrado.
    Inventario insuficiente.
Autor: Jonnattan Arley González Ruiz
Versión: 1.0.0
