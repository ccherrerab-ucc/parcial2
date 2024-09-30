/*package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import com.GRH.myapp.GRH_myapp.service.SeguimientoSolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimiento")
public class SeguimientosSolicitudesController {

    @Autowired
    private SeguimientoSolicitudesService seguimientoSolicitudesService;

    // Endpoint para obtener todas las solicitudes de seguimiento
    @GetMapping
    public ResponseEntity<List<SeguimientoSolicitudes>> getAll() {
        List<SeguimientoSolicitudes> solicitudes = seguimientoSolicitudesService.getAll();
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    // Endpoint para actualizar el estado de una solicitud
    @PutMapping("/{idGuid}/status")
    public ResponseEntity<SeguimientoSolicitudes> updateStatus(
            @PathVariable Integer idGuid,
            @RequestParam Integer statusCode) {
        try {
            SeguimientoSolicitudes updatedSeguimiento = seguimientoSolicitudesService.updateStatus(idGuid, statusCode);
            return new ResponseEntity<>(updatedSeguimiento, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para crear una nueva solicitud de seguimiento
    @PostMapping
    public ResponseEntity<SeguimientoSolicitudes> createSeguimiento(@RequestBody SeguimientoSolicitudes seguimiento) {
        SeguimientoSolicitudes savedSeguimiento = seguimientoSolicitudesService.save(seguimiento);
        return new ResponseEntity<>(savedSeguimiento, HttpStatus.CREATED);
    }
}*/
