package com.example.reportes_service.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Runo {
    String nombreR;

    Integer sedanCantidad;
    Double sedanTotal;

    Integer hatchCantidad;
    Double hatchTotal;

    Integer suvCantidad;
    Double suvTotal;

    Integer pickupCantidad;
    Double pickupTotal;

    Integer furgonetaCantidad;
    Double furgonetaTotal;



}
