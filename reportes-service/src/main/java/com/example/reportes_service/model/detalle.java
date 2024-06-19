package com.example.reportes_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class detalle {

    private String patente;
    private String reparacion;
    private LocalDate fechaI;
    private LocalTime horaI;
    private Double monto;

}
