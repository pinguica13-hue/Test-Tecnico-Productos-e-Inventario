# 🛒 Microservicios: MSProductos y MSInventario

Este proyecto implementa un sistema básico de microservicios para la gestión de **productos** y **inventario**.  
Los servicios se comunican entre sí mediante **HTTP con JSON API**, con autenticación basada en **API Keys**,  
y se documentan mediante **Swagger / OpenAPI**.

---
## 📌 Arquitectura General

```mermaid
flowchart LR
    A[Cliente / Postman] -->|HTTP JSON| B[MSProductos]
    A -->|HTTP JSON| C[MSInventario]
    C -->|Consulta Producto| B
