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

    public void deleteById(String id) {
        seguimientosSolicitudesRepository.deleteById(id);
    }

    public Optional<SeguimientosSolicitudes> findById(String id) {
        return seguimientosSolicitudesRepository.findById(id);
    }
    public SeguimientosSolicitudes updateRequest(String id, SeguimientosSolicitudes updatedSolicitud) throws Exception {
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

        // Marcar la solicitud actual como no vigente
        existingSolicitud.setVigente(false);
        seguimientosSolicitudesRepository.save(existingSolicitud);

        // Crear una nueva solicitud basada en la solicitud actualizada
        SeguimientosSolicitudes newSolicitud = new SeguimientosSolicitudes();
        newSolicitud.setIdRequest(updatedSolicitud.getIdRequest()); // Asignar ID del tipo de solicitud
        newSolicitud.setStatusCode(updatedSolicitud.getStatusCode()); // Asignar el estado actualizado
        newSolicitud.setVigente(true); // El nuevo registro debe ser vigente
        newSolicitud.setCreationDate(new Date()); // Asignar la fecha actual como fecha de creación

        // Guardar la nueva solicitud en la base de datos
        return seguimientosSolicitudesRepository.save(newSolicitud);
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
/*package com.GRH.myapp.GRH_myapp.service;

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
}*/

