package com.example.msinventario.controller;

import com.example.msinventario.dto.CompraRequest;
import com.example.msinventario.dto.CompraResponse;
import com.example.msinventario.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController 
{
    private final CompraService compraService;
    @PostMapping
    public ResponseEntity<CompraResponse> comprar(@RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.realizarCompra(request));
    }
}
