package com.example.reparaciones_service.service;


import com.example.reparaciones_service.entity.Reparaciones;
import com.example.reparaciones_service.repository.ReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparacionesService {

    @Autowired
    ReparacionesRepository reparacionesRepository;

    //guardar reparacion
    public Reparaciones guardar (Reparaciones reparacion){
        Reparaciones nuevaReparacion = reparacionesRepository.save(reparacion);
        return nuevaReparacion;
    }

    //actualizar reparacion

    //listar todas las reparaciones
    public List<Reparaciones> todo(){
        List<Reparaciones> lista = reparacionesRepository.findAll();
        return lista;
    }
}
