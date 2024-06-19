package com.example.vehiculo_service.repository;

import com.example.vehiculo_service.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Vehiculo findByPatente(String patente);

    List<Vehiculo> findAll();


}
