package com.example.reparaciones_service.repository;

import com.example.reparaciones_service.entity.Reparaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionesRepository extends JpaRepository <Reparaciones, Integer> {
    void update(Reparaciones reparacion);
}
