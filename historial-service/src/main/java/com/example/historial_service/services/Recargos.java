package com.example.historial_service.services;

import com.example.historial_service.entity.HistorialEntity;
import com.example.historial_service.model.vehiculo;
import com.example.historial_service.repository.DetalleRepository;
import com.example.historial_service.repository.HistorialRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class Recargos {

    @Autowired
    DetalleRepository detalleRepository;

    @Autowired
    DetalleService detalleService;

    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    RestTemplate restTemplate;

   // @Autowired
    //HistorialService historialService;

    //Recargo por kilometraje
    public Double recargoKilometraje(String patente, LocalDate ingreso) {
        vehiculo actual = restTemplate.getForObject("http://vehiculo-service/vehiculo/patente/" + patente, vehiculo.class);
        Double kilometraje = actual.getKilometraje();
        String tipo = actual.getTipo();
        Double recargo = 0.0;
        Double total = detalleService.montoTotal(patente, ingreso);
        if (kilometraje > 0.0) {
            if (kilometraje < 50000) {
                recargo = 0.0;
            } else if (kilometraje >= 5001 && kilometraje <= 12000) {
                if (tipo.toUpperCase().equals("SEDAN") || tipo.toUpperCase().equals("HATCHBACK")) {
                    recargo = total * 0.03;
                } else if (tipo.toUpperCase().equals("SUV") || tipo.toUpperCase().equals("PICKUP") || tipo.toUpperCase().equals("FURGONETA")) {
                    recargo = total * 0.05;
                }
            } else if (kilometraje >= 12001 && kilometraje <= 25000) {
                if (tipo.toUpperCase().equals("SEDAN") || tipo.toUpperCase().equals("HATCHBACK")) {
                    recargo = total * 0.07;
                } else if (tipo.toUpperCase().equals("SUV") || tipo.toUpperCase().equals("PICKUP") || tipo.toUpperCase().equals("FURGONETA")) {
                    recargo = total * 0.09;
                }
            } else if (kilometraje >= 25001 && kilometraje <= 40000) {
                if (tipo.toUpperCase().equals("SUV") ||
                        tipo.toUpperCase().equals("PICKUP") ||
                        tipo.toUpperCase().equals("FURGONETA") ||
                        tipo.toUpperCase().equals("SEDAN") ||
                        tipo.toUpperCase().equals("HATCHBACK")) {
                    recargo = total * 0.12;
                }
            } else if (kilometraje >= 40000) {
                if (tipo.toUpperCase().equals("SUV") ||
                        tipo.toUpperCase().equals("PICKUP") ||
                        tipo.toUpperCase().equals("FURGONETA") ||
                        tipo.toUpperCase().equals("SEDAN") ||
                        tipo.toUpperCase().equals("HATCHBACK")) {
                    recargo = total * 0.20;
                }
            }
        }
        return recargo;
    }

    //Recargo por antiguedad
    public Double recargoAntiguedad(String patente, LocalDate ingreso){
        vehiculo actual = restTemplate.getForObject("http://vehiculo-service/vehiculo/patente/" + patente, vehiculo.class);
        Integer ano_fab = actual.getAno_fab();
        Integer ano_actual = LocalDate.now().getYear();
        Integer antiguedad = ano_actual - ano_fab;
        String tipo = actual.getTipo();
        Double total = detalleService.montoTotal(patente, ingreso);
        Double recargo = 0.0;
        if (antiguedad <= 5) {
            recargo = 0.0;
        }
        if (antiguedad >= 6 && antiguedad <= 10) {
            if (tipo.toUpperCase().equals("SEDAN") || tipo.toUpperCase().equals("HATCHBACK")) {
                recargo = total * 0.05;
            } else if (tipo.toUpperCase().equals("SUV") || tipo.toUpperCase().equals("PICKUP") || tipo.toUpperCase().equals("FURGONETA")) {
                recargo = total * 0.07;
            }
        } else if (antiguedad >= 11 && antiguedad <= 15) {
            if (tipo.toUpperCase().equals("SEDAN") || tipo.toUpperCase().equals("HATCHBACK")) {
                recargo = total * 0.09;
            } else if (tipo.toUpperCase().equals("SUV") || tipo.toUpperCase().equals("PICKUP") || tipo.toUpperCase().equals("FURGONETA")) {
                recargo = total * 0.11;
            }
        } else if (antiguedad <= 16) {
            if (tipo.toUpperCase().equals("SEDAN") || tipo.toUpperCase().equals("HATCHBACK")) {
                recargo = total * 0.15;
            } else if (tipo.toUpperCase().equals("SUV") || tipo.toUpperCase().equals("PICKUP") || tipo.toUpperCase().equals("FURGONETA")) {
                recargo = total * 0.20;
            }
        }
        return recargo;
    }

    //Recargo por atraso de recogida
    public Double recargoDespacho(String patente, LocalDate ingreso){
        LocalDate despachoF = LocalDate.now();
        LocalTime despachoH = LocalTime.now();
        HistorialEntity actual = historialRepository.findByPatenteAndFechaI(patente, ingreso);
        actual.setFechaD(despachoF);
        actual.setHoraD(despachoH);
        LocalDate salida = actual.getFechaS();
        Integer retrasos = Math.toIntExact((DAYS.between(salida, despachoF)));
        Double total = detalleService.montoTotal(patente, ingreso);
        Double recargo = total * (retrasos * 0.05);

        return recargo;
    }



}
