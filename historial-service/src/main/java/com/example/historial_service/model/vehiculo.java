package com.example.historial_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private String tipo; // SUV, PICKUP,SEDAN, HATCHBACK, FURGONETA
    private Integer ano_fab;
    private String motor; // DIESEL, GASOLINA, HIBRIDO, ELECTRICO
    private Integer asientos;
    private Double kilometraje;
}
