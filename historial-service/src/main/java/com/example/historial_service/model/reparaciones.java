package com.example.historial_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class reparaciones {
    private String nombre;
    private String descripcion;
    private Integer gasolina;
    private Integer diesel;
    private Integer hibrido;
    private Integer electrico;

}
