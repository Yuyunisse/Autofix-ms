package com.example.vehiculo_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patente;
    private String marca;
    private String modelo;
    private String tipo; // SUV, PICKUP,SEDAN, HATCHBACK, FURGONETA
    private Integer ano_fab;
    private String motor; // DIESEL, GASOLINA, HIBRIDO, ELECTRICO
    private Integer asientos;
    private Double kilometraje; // ACTUALIZABLE
}
