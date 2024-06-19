package com.example.reportes_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hcuatro {
    String patente;
    String marca;
    String modelo;
    String tipo;
    Integer ano;
    String motor;
    LocalDate fecha_ingreso;
    LocalTime hora_ingreso;
    Double total; //total de las reparaciones de la fecha indicada
    Double recargos;
    Double descuentos;
    Double subtotal; //total+recargos-descuentos
    Double iva;
    Double costoTotal; //subtotal + iva
    LocalDate fecha_salida;
    LocalTime hora_salida;
    LocalDate fecha_despacho;
    LocalTime hora_despacho;
}
