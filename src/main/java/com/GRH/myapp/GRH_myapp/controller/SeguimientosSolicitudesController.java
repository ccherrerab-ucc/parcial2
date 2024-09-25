package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import com.GRH.myapp.GRH_myapp.service.SeguimientoSolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seguimiento-solicitudes")
public class SeguimientosSolicitudesController {

    @Autowired
    private SeguimientoSolicitudesService seguimientoSolicitudesService;

    // Endpoint para actualizar el estado de la solicitud
    @PutMapping("/update-status/{idGuid}")
    public ResponseEntity<?> updateStatus(@PathVariable Integer idGuid, @RequestParam Integer statusCode) {
        try {
            // Llamar al servicio para actualizar el estado
            SeguimientoSolicitudes seguimientoActualizado = seguimientoSolicitudesService.updateStatus(idGuid, statusCode);
            return new ResponseEntity<>(seguimientoActualizado, HttpStatus.OK);
        } catch (IllegalStateException e) {
            // En caso de error, devolver una respuesta con el estado HTTP correspondiente
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Para cualquier otro error inesperado
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}