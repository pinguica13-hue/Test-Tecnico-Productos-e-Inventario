package com.example.msinventario.service;

import com.example.msinventario.dto.CompraRequest;
import com.example.msinventario.dto.CompraResponse;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.repository.InventarioRepository;
import com.example.msinventario.client.ProductoClient;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompraServiceTest {

    private final InventarioRepository repo = mock(InventarioRepository.class);
    private final ProductoClient productoClient = mock(ProductoClient.class);
    private final InventarioService inventarioService = new InventarioService(repo, productoClient);
    private final CompraService compraService = new CompraService(inventarioService, productoClient);

    @Test
    void realizarCompraExitosa() {
        Inventario inv = new Inventario();
        inv.setProductoId(1L);
        inv.setCantidad(10);

        when(repo.findByProductoId(1L)).thenReturn(Optional.of(inv));
        when(productoClient.getProducto(1L)).thenReturn(Map.of("id", 1L, "nombre", "Laptop", "precio", 1000.0));

        CompraRequest request = new CompraRequest(1L, 2);
        CompraResponse response = compraService.realizarCompra(request);

        assertEquals(1L, response.getProductoId());
        assertEquals(2, response.getCantidadComprada());
        assertEquals(2000.0, response.getTotal());
    }

    @Test
    void realizarCompraInventarioInsuficiente() {
        Inventario inv = new Inventario();
        inv.setProductoId(1L);
        inv.setCantidad(1);

        when(repo.findByProductoId(1L)).thenReturn(Optional.of(inv));
        when(productoClient.getProducto(1L)).thenReturn(Map.of("id", 1L, "nombre", "Laptop", "precio", 1000.0));

        CompraRequest request = new CompraRequest(1L, 5);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> compraService.realizarCompra(request));
        assertTrue(ex.getMessage().contains("Inventario insuficiente"));
    }
}
