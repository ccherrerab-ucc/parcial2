package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.SeguimientoDTO;
import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.SeguimientoSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SeguimientoSolicitudesService {

    @Autowired
    private SeguimientoSolicitudesRepository seguimientoSolicitudesRepository;

    public SeguimientoSolicitudes updateStatus(Integer idGuid, Integer statusCode) throws IllegalStateException {
        // Buscar el seguimiento por su ID
        Optional<SeguimientoSolicitudes> seguimientoOpt = seguimientoSolicitudesRepository.findById(idGuid);

        if (!seguimientoOpt.isPresent()) {
            throw new IllegalStateException("Seguimiento no encontrado con ID: " + idGuid);
        }

        SeguimientoSolicitudes seguimiento = seguimientoOpt.get();

        // Verificar si el campo validar es false, lo que significa que no se pueden hacer cambios
        if (!seguimiento.getVigente()) {
            throw new IllegalStateException("La solicitud no puede modificarse porque ha sido rechazada.");
        }

        // Actualizar el estado y aplicar la lógica de negocio
        seguimiento.getStatusCode().setStatusCode(statusCode);
        ;

        if (statusCode == 0) { // Estado RECHAZADO
            seguimiento.setVigente(false);
        } else if (statusCode == 1) { // Estado ACEPTADO o EN_PROCESO
            seguimiento.setVigente(true);
        }

        // Actualizar la fecha de seguimiento
        seguimiento.setCreationDate(new Date());

        return seguimientoSolicitudesRepository.save(seguimiento);
    }
}
