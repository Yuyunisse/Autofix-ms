package com.example.reportes_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class historial {
    private String patente;
    private LocalDate fechaI;
    private LocalTime horaI;
    private Double totalR;
    private Double recargos;
    private Double descuentos;
    private Double iva;
    private Double total;
    private LocalDate fechaS;
    private LocalTime horaS;
    private LocalDate fechaD;
    private LocalTime horaD;

}
