package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.service.SeguimientosSolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos_solicitudes")
public class SeguimientosSolicitudesController {

    @Autowired
    private SeguimientosSolicitudesService seguimientosSolicitudesService;

    @GetMapping
    public List<SeguimientosSolicitudes> getAll() {
        return seguimientosSolicitudesService.findAll();
    }

    @PostMapping
    public SeguimientosSolicitudes create(@RequestBody SeguimientosSolicitudes solicitud) {
        return seguimientosSolicitudesService.save(solicitud);
    }
    @PutMapping("/{id}")
    public SeguimientosSolicitudes update(@PathVariable Integer id, @RequestBody SeguimientosSolicitudes solicitud) {
        try {
            return seguimientosSolicitudesService.updateRequest(id, solicitud);
        } catch (Exception e) {
            // Manejo de la excepción
            System.out.println("Error al actualizar la solicitud: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        seguimientosSolicitudesService.deleteById(id);
    }
}
