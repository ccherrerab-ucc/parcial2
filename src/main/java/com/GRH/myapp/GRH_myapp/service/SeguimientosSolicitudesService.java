package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeguimientosSolicitudesService {

    @Autowired
    private SeguimientosSolicitudesRepository seguimientosSolicitudesRepository;

    public List<SeguimientosSolicitudes> findAll() {
        return seguimientosSolicitudesRepository.findAll();
    }

    public SeguimientosSolicitudes save(SeguimientosSolicitudes solicitud) {
        return seguimientosSolicitudesRepository.save(solicitud);
    }

    public void deleteById(Integer id) {
        seguimientosSolicitudesRepository.deleteById(id);
    }

    public Optional<SeguimientosSolicitudes> findById(Integer id) {
        return seguimientosSolicitudesRepository.findById(id);
    }

    public SeguimientosSolicitudes updateRequest(Integer id, SeguimientosSolicitudes updatedSolicitud) throws Exception {
        Optional<SeguimientosSolicitudes> optionalSolicitud = seguimientosSolicitudesRepository.findById(id);

        if (!optionalSolicitud.isPresent()) {
            throw new Exception("Solicitud no encontrada.");
        }

        SeguimientosSolicitudes existingSolicitud = optionalSolicitud.get();

        // Verificar si el status_code está en "pendiente" o es igual a 1
        if (existingSolicitud.getStatusCode().getStatusCode() != 1) {
            throw new Exception("La solicitud no puede modificarse porque no está en estado pendiente.");
        }

        // No permitir la modificación de la fecha de creación o del status_code
        updatedSolicitud.setCreationDate(existingSolicitud.getCreationDate());
        updatedSolicitud.setStatusCode(existingSolicitud.getStatusCode());

        // No permitir la modificación del campo vigente
        updatedSolicitud.setVigente(existingSolicitud.getVigente());

        // Guardar los cambios en la base de datos
        return seguimientosSolicitudesRepository.save(updatedSolicitud);
    }
    
    // Método para obtener todas las solicitudes ordenadas
    public List<SeguimientosSolicitudes> findAllOrdered(String orderByField, boolean asc) {
        Sort sort = asc ? Sort.by(orderByField).ascending() : Sort.by(orderByField).descending();
        return seguimientosSolicitudesRepository.findAll(sort);
    }
}

