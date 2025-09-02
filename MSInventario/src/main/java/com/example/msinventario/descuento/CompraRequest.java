package com.example.msinventario.dto;

import lombok.Data;

@Data
public class CompraRequest 
{
    private Long productoId;
    private Integer cantidad;
}
