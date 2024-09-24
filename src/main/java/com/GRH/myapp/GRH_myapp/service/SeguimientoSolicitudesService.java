package com.GRH.myapp.GRH_myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SeguimientoSolicitudSService {

    @Autowired
    private SeguimientoSolicitudRepository SeguimientosolicitudRepository;

    public SeguimientoSolicitud agregarSolicitud(Long idRequest, Long idEstatus, boolean vigente) {
        SeguimientoSolicitud nuevaSolicitud = new SeguimientoSolicitud(idRequest, idEstatus, vigente);
        return solicitudRepository.save(nuevaSolicitud); // Guardamos la nueva solicitud
    }

    public Optional<SeguimientoSolicitud> obtenerSolicitudPorId(Long id) {
        return SeguimientosolicitudRepository.findById(id); // Buscamos una solicitud por su ID
    }
}
