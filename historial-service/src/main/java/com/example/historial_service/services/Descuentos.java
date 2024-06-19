package com.example.historial_service.services;

import com.example.historial_service.entity.DetalleEntity;
import com.example.historial_service.entity.HistorialEntity;
import com.example.historial_service.model.vehiculo;
import com.example.historial_service.repository.DetalleRepository;
import com.example.historial_service.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDate;
import java.util.List;

@Service
public class Descuentos {
    @Autowired
    DetalleRepository detalleRepository;

    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HistorialService historialService;




    //Por cantidad de reparaciones (12 meses) basado en el detalle
    public Double descuentoCantReparaciones(String patente, LocalDate ingreso){
        List<DetalleEntity> reparaciones = detalleRepository.findAllByPatente(patente);
        Integer cantReparaciones = reparaciones.size();
        Double total = historialService.montoTotal(patente, ingreso);
        Integer contador = 0;
        Double descuento = 0.0;
        vehiculo actual = restTemplate.getForObject("http://vehiculo-service/vehiculo/patente/" + patente, vehiculo.class);
        String motor = actual.getMotor();
        for (DetalleEntity elemento : reparaciones){
            if ((DAYS.between((elemento.getFechaI()),LocalDate.now()) <= 365)){
                contador = contador + 1;
            }
        }
        if (contador < 3){
            if (contador == 1 || contador == 2) {
                if (motor.toUpperCase().equals("GASOLINA")) {
                    descuento = total * 0.05;
                } else if (motor.toUpperCase().equals("DIESEL")) {
                    descuento = total * 0.07;
                } else if (motor.toUpperCase().equals("HIBRIDO")) {
                    descuento = total * 0.10;
                } else if (motor.toUpperCase().equals("ELECTRICO")) {
                    descuento = total * 0.08;
                }
            }
            else if (contador >= 3 && contador <= 5) {
                if (motor.toUpperCase().equals("GASOLINA")) {
                    descuento = total * 0.10;
                } else if (motor.toUpperCase().equals("DIESEL")) {
                    descuento = total * 0.12;
                } else if (motor.toUpperCase().equals("HIBRIDO")) {
                    descuento = total * 0.15;
                } else if (motor.toUpperCase().equals("ELECTRICO")) {
                    descuento = total * 0.13;
                }
            }
            else if (contador >=6 && contador <= 9) {
                if (motor.toUpperCase().equals("GASOLINA")) {
                    descuento = total * 0.15;
                } else if (motor.toUpperCase().equals("DIESEL")) {
                    descuento = total * 0.17;
                } else if (motor.toUpperCase().equals("HIBRIDO")) {
                    descuento = total * 0.20;
                } else if (motor.toUpperCase().equals("ELECTRICO")) {
                    descuento = total * 0.18;
                }
            }
            else if (contador >= 10) {
                if (motor.toUpperCase().equals("GASOLINA")) {
                    descuento = total * 0.20;
                } else if (motor.toUpperCase().equals("DIESEL")) {
                    descuento = total * 0.22;
                } else if (motor.toUpperCase().equals("HIBRIDO")) {
                    descuento = total * 0.25;
                } else if (motor.toUpperCase().equals("ELECTRICO")) {
                    descuento = total * 0.23;
                }

            }

        }
        return descuento;
    }

    //Descuento por dia de atencion
    //hora 09.00 - 12.00
    //Lunes o jueves: 10%
    public Double descuentoDia(String patente, LocalDate ingreso){
        HistorialEntity actual = (HistorialEntity) historialRepository.findByPatenteAndFechaI(patente, ingreso);
        String dia = String.valueOf(ingreso.getDayOfWeek());
        Integer hora = Integer.valueOf(actual.getHoraI().getHour());
        Double descuento = 0.0;
        Double total = historialService.montoTotal(patente,ingreso);
        if (dia.toUpperCase().equals("LUNES") || dia.toUpperCase().equals("MONDAY") || dia.toUpperCase().equals("JUEVES") || dia.toUpperCase().equals("THURSDAY")){
            if (hora <= 9 && hora <= 12){
                descuento = total * 0.10;
            }
        }
        return descuento;

    }



}
