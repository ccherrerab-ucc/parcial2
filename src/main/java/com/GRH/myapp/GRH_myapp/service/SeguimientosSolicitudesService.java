package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;

import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import com.GRH.myapp.GRH_myapp.repository.StatusSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeguimientosSolicitudesService {

    @Autowired
    private SeguimientosSolicitudesRepository seguimientosSolicitudesRepository;
    @Autowired
    private StatusSolicitudesRepository statusSolicitudesRepository;


    public List<SeguimientosSolicitudes> findAll() {
        return seguimientosSolicitudesRepository.findAll();
    }

    public SeguimientosSolicitudes save(SeguimientosSolicitudes solicitud) {
        return seguimientosSolicitudesRepository.save(solicitud);
    }

    public void deleteById(String id) {
        seguimientosSolicitudesRepository.deleteById(id);
    }
    

    public Optional<SeguimientosSolicitudes> findById(String id) {
        return seguimientosSolicitudesRepository.findById(id);
    }
    public void handleSeguimientosForRequestUpdate_old(Request request) 
    {
        // Paso 1: Encontrar el último Seguimiento y marcarlo como no vigente (vigente = false)
        Optional<SeguimientosSolicitudes> ultimoSeguimientoOpt = seguimientosSolicitudesRepository.findTopByIdRequestOrderByCreationDateDesc(request);

        if (ultimoSeguimientoOpt.isPresent()) {
            SeguimientosSolicitudes ultimoSeguimiento = ultimoSeguimientoOpt.get();
            ultimoSeguimiento.setVigente(false);  // Marcar como no vigente
            seguimientosSolicitudesRepository.save(ultimoSeguimiento);
        }

        // Paso 2: Crear un nuevo seguimiento marcado como vigente
        SeguimientosSolicitudes nuevoSeguimiento = new SeguimientosSolicitudes();
        nuevoSeguimiento.setIdGuid(UUID.randomUUID().toString());
        nuevoSeguimiento.setVigente(true);  // El nuevo seguimiento es el vigente
        nuevoSeguimiento.setCreationDate(new Date());
        nuevoSeguimiento.setStatusCode(new StatusSolicitudes());  // O cualquier otro estado necesario
        nuevoSeguimiento.setIdRequest(request);  // Asociar el seguimiento con la solicitud actualizada

        // Guardar el nuevo seguimiento
        seguimientosSolicitudesRepository.save(nuevoSeguimiento);
    }
    
    public void handleSeguimientosForRequestUpdate(Request request) {
        // Paso 1: Encontrar el último Seguimiento y marcarlo como no vigente (vigente = false)
        Optional<SeguimientosSolicitudes> ultimoSeguimientoOpt = seguimientosSolicitudesRepository.findTopByIdRequestOrderByCreationDateDesc(request);

        if (ultimoSeguimientoOpt.isPresent()) {
            SeguimientosSolicitudes ultimoSeguimiento = ultimoSeguimientoOpt.get();
            ultimoSeguimiento.setVigente(false);  // Marcar como no vigente
            seguimientosSolicitudesRepository.save(ultimoSeguimiento);
        }

        // Paso 2: Crear un nuevo seguimiento marcado como vigente
        SeguimientosSolicitudes nuevoSeguimiento = new SeguimientosSolicitudes();
        nuevoSeguimiento.setIdGuid(UUID.randomUUID().toString());
        nuevoSeguimiento.setVigente(true);  // El nuevo seguimiento es el vigente
        nuevoSeguimiento.setCreationDate(new Date());

        // Obtener el estado actualizado desde la base de datos
        StatusSolicitudes statusActualizado = statusSolicitudesRepository.findByStatusCode(1)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado: UPDATED"));

        nuevoSeguimiento.setStatusCode(statusActualizado);  // Asignar el estado de solicitudes desde la BD
        nuevoSeguimiento.setIdRequest(request);  // Asociar el seguimiento con la solicitud actualizada

        // Guardar el nuevo seguimiento
        
        seguimientosSolicitudesRepository.save(nuevoSeguimiento);
    }
    public SeguimientosSolicitudes updateRequest_old(String id, SeguimientosSolicitudes updatedSolicitud) throws Exception {
        Optional<SeguimientosSolicitudes> optionalSolicitud = seguimientosSolicitudesRepository.findById(id);

        if (!optionalSolicitud.isPresent()) {
            throw new Exception("Solicitud no encontrada.");
        }

        SeguimientosSolicitudes existingSolicitud = optionalSolicitud.get();

        // Verificar si la solicitud es vigente (si vigente es false, no se puede modificar)
        if (!existingSolicitud.getVigente()) {
            throw new Exception("La solicitud no es vigente y no puede ser modificada.");
        }

        // Lógica de negocio: solo se puede cambiar el estado si el estado actual es 1 (pendiente)
        if (existingSolicitud.getStatusCode().getStatusCode() != 1 && updatedSolicitud.getStatusCode().getStatusCode() != 1) {
            throw new Exception("La solicitud no puede cambiar de estado, ya que no está en estado pendiente.");
        }

        // Si el nuevo estado es 0, marcar como no vigente
        if (updatedSolicitud.getStatusCode().getStatusCode() == 0) {
            updatedSolicitud.setVigente(false);
        }

        // Mantener la fecha de creación original y otros campos no modificables
        updatedSolicitud.setCreationDate(existingSolicitud.getCreationDate());

        // Guardar los cambios en la base de datos
        return seguimientosSolicitudesRepository.save(updatedSolicitud);
    }
    

    
    // Método para obtener todas las solicitudes ordenadas
    public List<SeguimientosSolicitudes> findAllOrdered(String orderByField, boolean asc) {
        Sort sort = asc ? Sort.by(orderByField).ascending() : Sort.by(orderByField).descending();
        return seguimientosSolicitudesRepository.findAll(sort);
    }
}


