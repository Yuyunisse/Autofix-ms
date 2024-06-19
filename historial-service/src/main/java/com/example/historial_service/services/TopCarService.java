package com.example.historial_service.services;


import com.example.historial_service.entity.BonoEntity;
import com.example.historial_service.entity.Convenio;
import com.example.historial_service.entity.HistorialEntity;
import com.example.historial_service.model.vehiculo;
import com.example.historial_service.repository.BonoRepository;
import com.example.historial_service.repository.ConvenioRepository;
import com.example.historial_service.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class TopCarService {

    @Autowired
    ConvenioRepository convenioRepository;

    @Autowired
    BonoRepository bonoRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HistorialRepository historialRepository;

    public Convenio agregar(Convenio nuevoRegistro){
        convenioRepository.save(nuevoRegistro);
        return nuevoRegistro;
    }

    //revisar si hay cupo de bono
    //si hay hace el descuento de la cantidad si no no hace nada
    //y retorna el monto correspondiente
    public Integer existeCupo(String marca, LocalDate fecha){
        List<Convenio> cupos = convenioRepository.findAllByAnioAndMes(fecha.getYear(),fecha.getMonthValue());
        Integer monto = 0;
        for(Convenio cupo : cupos) {
            if (cupo.getMarca() == marca) {
                cupo.setCantidad(cupo.getCantidad() - 1);
                monto = cupo.getMonto();
            }
        }
        return monto;
    }

    //Descuento por Bonos
    public Integer descuentoBono(String patente, LocalDate ingreso) {
        Integer descuento = 0;
        List<BonoEntity> listaPatente = bonoRepository.findAllByPatente(patente);
        vehiculo auto = restTemplate.getForObject("http://vehiculo-service/vehiculo/patente/" + patente, vehiculo.class);
        String marca = auto.getMarca();;
        BonoEntity bonoAux = new BonoEntity();
        if (listaPatente != null) {
            for (BonoEntity bono : listaPatente) {
                if ((bono.getFecha().getMonthValue() == ingreso.getMonthValue()) &&
                        (bono.getFecha().getYear() == ingreso.getYear())) {
                    descuento = 0;
                }
            }
            if (bonoAux == null) {
                descuento = existeCupo(marca, ingreso);
                bonoAux.setFecha(ingreso);
                bonoAux.setPatente(patente);
                bonoRepository.save(bonoAux);
            }
        }
        else{
            descuento = existeCupo(marca, ingreso);
            bonoAux.setFecha(ingreso);
            bonoAux.setPatente(patente);
            bonoRepository.save(bonoAux);

        }
        return descuento;

    }




}

