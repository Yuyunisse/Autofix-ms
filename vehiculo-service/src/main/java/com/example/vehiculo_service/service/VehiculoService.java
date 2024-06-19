package com.example.vehiculo_service.service;

import com.example.vehiculo_service.entity.Vehiculo;
import com.example.vehiculo_service.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    //guardar-crear
    public Vehiculo guardar(Vehiculo vehiculo){
        Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
        return nuevoVehiculo;
    }

    //conseguir por patente
    public Vehiculo buscarPorPatente(String patente){
        return vehiculoRepository.findByPatente(patente);

    }

    //conseguir todos los vehiculos
    public List<Vehiculo> buscarTodo(){
        return vehiculoRepository.findAll();
    }

    //actualizar Kilometraje


}
