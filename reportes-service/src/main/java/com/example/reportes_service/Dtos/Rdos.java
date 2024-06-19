package com.example.reportes_service.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rdos {
    //general
    String nombreReparacion;

    //mes1 (variacion desde mes2 a mes1)
    String mes1;
    Double cantidad1;
    Double monto1;
    String variacionCantidad1;
    String variacionMonto1;


    //mes2  (variacion desde mes3 a mes2)
    String mes2;
    Double cantidad2;
    Double monto2;
    String variacionCantidad2;
    String variacionMonto2;


    //mes3
    String mes3;
    Double cantidad3;
    Double monto3;

}
