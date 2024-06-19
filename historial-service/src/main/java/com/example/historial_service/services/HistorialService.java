package com.example.historial_service.services;

import com.example.historial_service.Dtos.Hcuatro;
import com.example.historial_service.config.RestTemplateConfig;
import com.example.historial_service.entity.DetalleEntity;
import com.example.historial_service.entity.HistorialEntity;
import com.example.historial_service.model.vehiculo;
import com.example.historial_service.repository.DetalleRepository;
import com.example.historial_service.repository.HistorialRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    DetalleRepository detalleRepository;

    @Autowired
    Recargos recargos;

    @Autowired
    Descuentos descuentos;

    @Autowired
    TopCarService topCarService;

    @Autowired
    RestTemplate restTemplate;

    //crear-guardar historial
    public HistorialEntity guardar(HistorialEntity historial){
        HistorialEntity nuevoHistorial = historialRepository.save(historial);
        return nuevoHistorial;
    }

    //actualizar historial
    public HistorialEntity actualizar(HistorialEntity historial){
        historialRepository.update(historial);
        return historial;
    }

    //Listar los historiales de una patente
    public List<HistorialEntity> listarPatente(String patente){
        List<HistorialEntity> listaHistorial = historialRepository.findAllByPatente(patente);
        return listaHistorial;
    }

    //Listar todos los historiales
    public List<HistorialEntity> listarTodo(){
        List<HistorialEntity> todo = historialRepository.findAll();
        return todo;
    }

    //Monto total de las reparaciones
    public Double montoTotal(String patente, LocalDate ingreso){
        List<DetalleEntity> totalReparaciones = detalleRepository.findAllByPatenteAndFechaI(patente,ingreso);
        Double total = 0.0;
        for (DetalleEntity elemento : totalReparaciones) {
            total = total + elemento.getMonto();
        }
        return total;
    }

    //Costo total con los descuentos y recargos
    public Double costoFinal(String patente, LocalDate ingreso){

        //calculo descuento de bonos
        Integer bono = topCarService.descuentoBono(patente,ingreso);

        Double recargo = recargos.recargoAntiguedad(patente, ingreso)+
                recargos.recargoDespacho(patente, ingreso) +
                recargos.recargoDespacho(patente, ingreso);


        Double descuento = descuentos.descuentoDia(patente, ingreso) +
                descuentos.descuentoCantReparaciones(patente, ingreso) + bono;

        HistorialEntity historialActual = historialRepository.findByPatenteAndFechaI(patente, ingreso);
        historialActual.setDescuentos(descuento);
        historialActual.setRecargos(recargo);

        Double totalRep = montoTotal(patente, ingreso);
        historialActual.setTotalR(totalRep);

        Double iva = totalRep * 0.19;
        historialActual.setIva(iva);

        Double costo = (totalRep + recargo - descuento) + iva ;
        historialActual.setTotal(costo);

        return costo;

    }

    //HU4 Listado de la informacion del vehiculo x historial
    public List<Hcuatro> historiaCuatro(){
        List<HistorialEntity> historiales = listarTodo();
        List<Hcuatro> informacion = new ArrayList<>();
        for (HistorialEntity elemento : historiales) {
            vehiculo actual = restTemplate.getForObject("http://vehiculo-service/vehiculo/patente/" + elemento.getPatente(), vehiculo.class);
            Hcuatro estructura = new Hcuatro();
            estructura.setPatente(elemento.getPatente());
            estructura.setMarca(actual.getMarca());
            estructura.setModelo(actual.getModelo());
            estructura.setTipo(actual.getTipo());
            estructura.setAno(actual.getAno_fab());
            estructura.setMotor(actual.getMotor());
            estructura.setFecha_ingreso(elemento.getFechaI());
            estructura.setHora_ingreso(elemento.getHoraI());
            estructura.setTotal(montoTotal(elemento.getPatente(), elemento.getFechaI()));
            estructura.setIva(elemento.getIva());
            estructura.setDescuentos(elemento.getDescuentos());
            estructura.setSubtotal(elemento.getTotalR() + elemento.getRecargos() - elemento.getDescuentos() );
            estructura.setIva(elemento.getIva());
            estructura.setCostoTotal(elemento.getTotal());
            estructura.setFecha_salida(elemento.getFechaS());
            estructura.setHora_salida(elemento.getHoraS());
            estructura.setFecha_despacho(elemento.getFechaD());
            estructura.setHora_despacho(elemento.getHoraD());
            informacion.add(estructura);
        }
        return informacion;
    }






}
