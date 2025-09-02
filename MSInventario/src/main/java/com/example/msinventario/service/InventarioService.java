package com.example.msinventario.service;

import com.example.msinventario.client.ProductoClient;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventarioService 
{
    private final InventarioRepository inventarioRepository;
    private final ProductoClient productoClient;

    public InventarioService(InventarioRepository inventarioRepository, ProductoClient productoClient) 
    {
        this.inventarioRepository = inventarioRepository;
        this.productoClient = productoClient;
    }

    public Optional<Inventario> getInventarioPorProducto(Long productoId) 
    {
        return inventarioRepository.findByProductoId(productoId);
    }

    public Inventario actualizarInventario(Long productoId, Integer nuevaCantidad) 
    {
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElseGet(() -> 
                {
                    Inventario nuevo = new Inventario();
                    nuevo.setProductoId(productoId);
                    return nuevo;
                });

        inventario.setCantidad(nuevaCantidad);
        return inventarioRepository.save(inventario);
    }

    public boolean existeProducto(Long productoId) 
    {
        try 
        {
            productoClient.getProducto(productoId);
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
}
