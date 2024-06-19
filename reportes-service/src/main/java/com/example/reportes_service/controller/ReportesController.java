package com.example.reportes_service.controller;

import com.example.reportes_service.Dtos.Rdos;
import com.example.reportes_service.Dtos.Runo;
import com.example.reportes_service.service.RdosService;
import com.example.reportes_service.service.RunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    @Autowired
    RunoService runoService;

    @Autowired
    RdosService rdosService;

    @GetMapping("/reporte1/{mes}/{ano}")
    public ResponseEntity<List<Runo>> mostrarReporteUno(@PathVariable Integer mes, Integer ano){
        List<Runo> reporte = runoService.reporteuno(mes,ano);
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/reporte2/{mes}/{ano}")
    public ResponseEntity<List<Rdos>> mostrarReporteDos(@PathVariable Integer mes, Integer ano){
        List<Rdos> reporteCantidad = rdosService.reportedosCantidad(mes,ano);
        List<Rdos> reporte = rdosService.reporteDos(reporteCantidad);
        return ResponseEntity.ok(reporte);
    }

}
