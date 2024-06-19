package com.example.historial_service.repository;

import com.example.historial_service.entity.DetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleEntity, Integer> {
    List<DetalleEntity> findAllByPatente(String patente);

    List<DetalleEntity> findAllByPatenteAndFechaI(String patente, LocalDate fechaI);

}
