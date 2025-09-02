#Sistema de Microservicios - Productos e Inventario

Este proyecto implementa un sistema de gestión de productos e inventario utilizando Spring Boot + JPA + MySQL/H2, siguiendo un enfoque de microservicios.

1. Microservicios

  1.MSProductos
    Gestión de productos.
    Endpoints para crear, consultar y listar productos.
    Base de datos propia (productos_db).
  
   2.MSInventario
    Gestión de inventario y compras.
    Consulta y actualización del stock de productos.
    Comunicación con MSProductos vía HTTP + API Key.
    Base de datos propia (inventario_db).

2. Arquitectura
flowchart LR
    A[Cliente / Frontend] -->|JSON API| B[MSProductos]
    A -->|JSON API| C[MSInventario]
    C -->|HTTP + API Key| B
    B -->|DB Productos| D[(MySQL / H2)]
    C -->|DB Inventario| E[(MySQL / H2)]

3.Comunicación entre microservicios por HTTP/JSON.

Autenticación básica con API Key.

Manejo de timeout y reintentos en el cliente REST.

🚀 Tecnologías

Java 17

Spring Boot 3.x

Spring Data JPA

MySQL / H2 (test)

Lombok

Swagger / OpenAPI

JUnit 5 + Mockito

📖 Documentación API

Swagger habilitado en ambos servicios:

MSProductos → http://localhost:8080/swagger-ui.html

MSInventario → http://localhost:8081/swagger-ui.html

📌 Endpoints principales
MSProductos

POST /productos → Crear producto.

GET /productos/{id} → Obtener producto por ID.

GET /productos → Listar todos los productos.

MSInventario

GET /inventario/{productoId} → Consultar stock de producto.

PUT /inventario/{productoId} → Actualizar stock de producto.

POST /compras → Realizar compra (verifica stock y descuenta inventario).

🔑 Seguridad (API Key)

La comunicación entre MSInventario → MSProductos utiliza autenticación con API Key.

Agregar en el application.yml de ms-inventario:

msproductos:
  url: http://localhost:8080
  apikey: my-secret-key

🧪 Testing

Se incluyen pruebas unitarias y de integración:

MSProductos

Creación de productos.

Consulta de productos.

MSInventario

Gestión de inventario.

Proceso de compra (incluye comunicación con MSProductos).

Manejo de errores:

Producto no encontrado.

Inventario insuficiente.

Ejemplo de ejecución:
mvn clean test

▶️ Ejecución
1. Clonar repositorio
git clone https://github.com/tuusuario/microservicios-productos-inventario.git
cd microservicios-productos-inventario

2. Levantar MSProductos
cd ms-productos
mvn spring-boot:run

3. Levantar MSInventario
cd ms-inventario
mvn spring-boot:run

🐳 Docker

Cada servicio cuenta con su propio Dockerfile.
Ejemplo de construcción y ejecución:

docker build -t ms-productos ./ms-productos
docker run -p 8080:8080 ms-productos

docker build -t ms-inventario ./ms-inventario
docker run -p 8081:8081 ms-inventario

📦 Futuras mejoras

Implementar mensajería (Kafka/RabbitMQ) para eventos de inventario.

Centralizar configuración con Spring Cloud Config.

Implementar Eureka / Service Discovery.

✨ Autores: Equipo de Desarrollo
📅 Versión: 1.0.0
