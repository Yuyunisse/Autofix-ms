package com.example.reportes_service.service;

import com.example.reportes_service.Dtos.Runo;
import com.example.reportes_service.model.Hcuatro;
import com.example.reportes_service.model.detalle;
import com.example.reportes_service.model.historial;
import com.example.reportes_service.model.reparaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RunoService {
    @Autowired
    RestTemplate restTemplate;

    public List<Runo> listaReparaciones(){
        List<reparaciones> listaRep = restTemplate.getForObject("http://reparaciones-service/reparaciones/listar"  , List.class);
        List<Runo> reporteBase = new ArrayList<>();
        for (reparaciones rep : listaRep){
            Runo estructura = new Runo();
            String nombre = rep.getNombre();
            estructura.setNombreR(nombre);
            reporteBase.add(estructura);
        }
        return reporteBase;
    }

    public List<Hcuatro> listaHistorial(Integer mes, Integer ano){
        List<Hcuatro> listaHist = restTemplate.getForObject("http://historial-service/historial/todo/vehiculoxhistorial"  , List.class);
        List<Hcuatro> listaReducida = new ArrayList<>();
        for (Hcuatro elemento : listaHist) {
            Integer mesHistorial = elemento.getFecha_ingreso().getMonthValue();
            Integer anoHistorial = elemento.getFecha_ingreso().getYear();
            if (mesHistorial == mes && anoHistorial == ano) {
                listaReducida.add(elemento);
            }
        }
        return listaReducida;
    }

    public List<Runo> reporteuno (Integer mes, Integer ano){
        List<Runo> reporteUno = listaReparaciones();
        List<Hcuatro> historial = listaHistorial(mes,ano);
        for (Hcuatro elemento : historial){
            String patente = elemento.getPatente();
            LocalDate ingreso = elemento.getFecha_ingreso();
            List<detalle> detalles = restTemplate.getForObject("http://historial-service/detalle/listar/" +patente+"/"+ingreso, List.class);
            for (detalle det : detalles){
                String reparacionDetalle = det.getReparacion();
                for (Runo elemetoR : reporteUno){
                    if (reparacionDetalle == elemetoR.getNombreR()){
                        if (elemento.getTipo().toUpperCase().equals("SEDAN")){
                            elemetoR.setSedanCantidad(elemetoR.getSedanCantidad() + 1);
                            elemetoR.setSedanTotal(elemetoR.getSedanTotal()+ det.getMonto());
                        }
                        else if (elemento.getTipo().toUpperCase().equals("HATCHBACK")){
                            elemetoR.setHatchCantidad(elemetoR.getHatchCantidad() + 1);
                            elemetoR.setHatchTotal(elemetoR.getHatchTotal()+ det.getMonto());
                        }
                        else if (elemento.getTipo().toUpperCase().equals("PICKUP")) {
                            elemetoR.setPickupCantidad(elemetoR.getPickupCantidad() + 1);
                            elemetoR.setPickupTotal(elemetoR.getPickupTotal() + det.getMonto());
                        }
                        else if (elemento.getTipo().toUpperCase().equals("SUV")) {
                            elemetoR.setSuvCantidad(elemetoR.getSuvCantidad() + 1);
                            elemetoR.setSuvTotal(elemetoR.getSuvTotal() + det.getMonto());
                        }
                        else if (elemento.getTipo().toUpperCase().equals("FURGONETA")) {
                            elemetoR.setFurgonetaCantidad(elemetoR.getFurgonetaCantidad() + 1);
                            elemetoR.setFurgonetaTotal(elemetoR.getFurgonetaTotal() + det.getMonto());
                        }

                    }
                }
            }

        }

        return reporteUno;
    }

}
