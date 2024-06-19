package com.example.vehiculo_service.controller;


import com.example.vehiculo_service.entity.Vehiculo;
import com.example.vehiculo_service.service.VehiculoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    VehiculoService vehiculoService;

    //Registrar nuevo vehiculo
    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoService.guardar(vehiculo);
        return ResponseEntity.ok("Vehiculo Registrado");
    }

    //Mostrar todos los vehiculos registrados y su informacion
    @GetMapping("/todo")
    public ResponseEntity<List<Vehiculo>> todo(){
        List<Vehiculo> mostrarTodo = vehiculoService.buscarTodo();
        return ResponseEntity.ok(mostrarTodo);
    }

    //Mostrar la informacion de un vehiculo por patente
    @GetMapping("/patente/{patente}")
    public ResponseEntity<Vehiculo> mostrarPatente(@PathVariable String patente){
        Vehiculo vehiculo = vehiculoService.buscarPorPatente(patente);
        return ResponseEntity.ok(vehiculo);
    }

}
