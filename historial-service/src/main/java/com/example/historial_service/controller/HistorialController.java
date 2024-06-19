package com.example.historial_service.controller;


import com.example.historial_service.Dtos.Hcuatro;
import com.example.historial_service.entity.DetalleEntity;
import com.example.historial_service.entity.HistorialEntity;
import com.example.historial_service.services.DetalleService;
import com.example.historial_service.services.HistorialService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @Autowired
    DetalleService detalleService;


//**********************+ controladores para HISTORIAL

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody HistorialEntity historial) {
        HistorialEntity nuevo = historialService.guardar(historial);
        return ResponseEntity.ok("Historial Registrado");
    }

    @GetMapping("/todo/vehiculoxhistorial")
    public ResponseEntity<List<Hcuatro>> vehiculoxhistorial(){
        List<Hcuatro> lista = historialService.historiaCuatro();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<HistorialEntity>> listarHistoriales(){
        List<HistorialEntity> listar = historialService.listarTodo();
        return ResponseEntity.ok(listar);
    }



//**********************+ controladores para DETALLE

    @PostMapping("/detalle/registrar")
    public ResponseEntity<String> registrar(@RequestBody DetalleEntity detalle) {
        DetalleEntity nuevo = detalleService.guardar(detalle);
        return ResponseEntity.ok("Detalle Reparacion Registrado");
    }

    @GetMapping("/detalle/listar/{patente}/{fecha}")
    public ResponseEntity<List<DetalleEntity>> listarDetalles(@PathVariable String patente, LocalDate fecha) {
        List<DetalleEntity> listar = detalleService.listarPatenteFecha(patente, fecha);
        return ResponseEntity.ok(listar);
    }


////**********************+ controladores para CONVENIO


////**********************+ controladores para BONO
    
}
