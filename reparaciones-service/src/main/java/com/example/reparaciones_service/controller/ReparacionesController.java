package com.example.reparaciones_service.controller;


import com.example.reparaciones_service.entity.Reparaciones;
import com.example.reparaciones_service.service.ReparacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparaciones")
public class ReparacionesController {
    @Autowired
    ReparacionesService reparacionesService;

    //Registrar nuevo vehiculo
    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Reparaciones reparacion) {
        Reparaciones nuevo = reparacionesService.guardar(reparacion);
        return ResponseEntity.ok("Nueva Reparacion agregada");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Reparaciones>> listartodo(){
        List<Reparaciones> mostrarTodo = reparacionesService.todo();
        return ResponseEntity.ok(mostrarTodo);
    }
}
