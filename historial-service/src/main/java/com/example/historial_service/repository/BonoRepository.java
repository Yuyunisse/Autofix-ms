package com.example.historial_service.repository;

import com.example.historial_service.entity.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonoRepository extends JpaRepository<BonoEntity, Integer> {

    List<BonoEntity> findAllByPatente(String patente);

}
