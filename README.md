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
   
3. Arquitectura
```mermaid
flowchart LR
    flowchart LR
    A[Cliente / Frontend] -->|JSON API| B[MSProductos]
    A -->|JSON API| C[MSInventario]
    C -->|HTTP + API Key| B
    B -->|DB Productos| D[(MySQL / H2)]
    C -->|DB Inventario| E[(MySQL / H2)]

