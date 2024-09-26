package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.SeguimientoSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeguimientoSolicitudesService {

    @Autowired
    private SeguimientoSolicitudesRepository seguimientoSolicitudesRepository;

    public SeguimientoSolicitudes updateStatus(Integer idGuid, Integer statusCode) throws IllegalStateException {
        // Buscar el seguimiento por su ID
        SeguimientoSolicitudes seguimiento = seguimientoSolicitudesRepository.findById(idGuid)
                .orElseThrow(() -> new IllegalStateException("Seguimiento no encontrado con ID: " + idGuid));

        // Verificar si el campo 'vigente' es false
        if (!seguimiento.getVigente()) {
            throw new IllegalStateException("La solicitud no puede modificarse porque ha sido rechazada.");
        }

        // Actualizar el estado
        seguimiento.getStatusCode().setStatusCode(statusCode);

        if (statusCode == 3) { // Estado RECHAZADO
            seguimiento.setVigente(false);
        } else { // Estado ACEPTADO o EN_PROCESO
            seguimiento.setVigente(true);
        }

        // Actualizar la fecha de seguimiento
        seguimiento.setCreationDate(new Date());

        // Lógica adicional para actualizar 'vigente'
        if (statusCode % 2 == 0) {
            seguimiento.setVigente(false);
        }

        return seguimientoSolicitudesRepository.save(seguimiento);
    }

    public List<SeguimientoSolicitudes> getAll() {
        return seguimientoSolicitudesRepository.findAll();
    }

    // Método para crear un nuevo seguimiento
    public SeguimientoSolicitudes save(SeguimientoSolicitudes seguimiento) {
        seguimiento.setCreationDate(new Date()); // Establecer fecha de creación
        return seguimientoSolicitudesRepository.save(seguimiento);
    }
}

