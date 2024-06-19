package com.example.historial_service.services;


import com.example.historial_service.entity.DetalleEntity;
import com.example.historial_service.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetalleService {

    @Autowired
    DetalleRepository detalleRepository;

    //Crear una reparacion
    public DetalleEntity guardar(DetalleEntity detalle){
        DetalleEntity nuevaReparacion = detalleRepository.save(detalle);
        return nuevaReparacion;
    }

    //listar las reparaciones de una patente
    public List<DetalleEntity> listarPatente(String patente){
        List<DetalleEntity> listaPatente = detalleRepository.findAllByPatente(patente);
        return listaPatente;
    }

    //Listar reparaciones de un historial
    public List<DetalleEntity> listarPatenteFecha(String patente, LocalDate fechaI){
        List<DetalleEntity> listaPyF = detalleRepository.findAllByPatenteAndFechaI(patente,fechaI);
        return listaPyF;
    }

}
