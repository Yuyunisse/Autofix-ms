package com.example.historial_service.repository;


import com.example.historial_service.entity.HistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<HistorialEntity, Integer> {


    List<HistorialEntity> findAllByPatente(String patente);

    HistorialEntity findByPatenteAndFechaI(String patente, LocalDate fechaI);
}
