package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import com.GRH.myapp.GRH_myapp.service.SeguimientosSolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seguimientos_solicitudes")
public class SeguimientosSolicitudesController {

    @Autowired
    private SeguimientosSolicitudesService seguimientosSolicitudesService;
     private SeguimientosSolicitudesRepository seguimientosSolicitudesRepository;


    @GetMapping
    public List<SeguimientosSolicitudes> getAll() {
        return seguimientosSolicitudesService.findAll();
    }

    @PostMapping
    public SeguimientosSolicitudes create(@RequestBody SeguimientosSolicitudes solicitud) {
        return seguimientosSolicitudesService.save(solicitud);
    }
    @PutMapping("/{id}")
    public SeguimientosSolicitudes updateRequest(Integer id, SeguimientosSolicitudes updatedSolicitud) throws Exception {
        Optional<SeguimientosSolicitudes> optionalSolicitud = seguimientosSolicitudesRepository.findById(id);

        if (!optionalSolicitud.isPresent()) {
            throw new Exception("Solicitud no encontrada.");
        }

        SeguimientosSolicitudes existingSolicitud = optionalSolicitud.get();

        // Lógica de negocio: sólo se puede cambiar de estado si el estado actual es 1 (pendiente)
        if (existingSolicitud.getStatusCode().getStatusCode() != 1 && updatedSolicitud.getStatusCode().getStatusCode() != 1) {
            throw new Exception("La solicitud no puede cambiar de estado, ya que no está en estado pendiente.");
        }

        // Lógica de negocio: si el nuevo estado es 0, marcar como no vigente
        if (updatedSolicitud.getStatusCode().getStatusCode() == 0) {
            updatedSolicitud.setVigente(false);
        }

        // Mantener la fecha de creación original y otros campos no modificables
        updatedSolicitud.setCreationDate(existingSolicitud.getCreationDate());

        // Guardar los cambios en la base de datos
        return seguimientosSolicitudesRepository.save(updatedSolicitud);
    }
    /*
    public SeguimientosSolicitudes update(@PathVariable Integer id, @RequestBody SeguimientosSolicitudes solicitud) {
        try {
            return seguimientosSolicitudesService.updateRequest(id, solicitud);
        } catch (Exception e) {
            // Manejo de la excepción
            System.out.println("Error al actualizar la solicitud: " + e.getMessage());
            return null;
        }
    }*/

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        seguimientosSolicitudesService.deleteById(id);
    }
}
