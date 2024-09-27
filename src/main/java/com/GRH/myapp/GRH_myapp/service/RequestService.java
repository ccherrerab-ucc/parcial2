package com.GRH.myapp.GRH_myapp.service;

import DTO.RequestDTO;
import DTO.SeguimientoSolicitudDTO;
import com.GRH.myapp.GRH_myapp.model.Employees;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;
import com.GRH.myapp.GRH_myapp.model.TypeRequest;
import com.GRH.myapp.GRH_myapp.repository.EmployeeRepository;
import com.GRH.myapp.GRH_myapp.repository.RequestRepository;
import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import com.GRH.myapp.GRH_myapp.repository.StatusSolicitudesRepository;
import com.GRH.myapp.GRH_myapp.repository.TypeRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private SeguimientosSolicitudesRepository seguimientoSolicitudRepository;
    
    @Autowired
    private TypeRequestRepository TypeRequestRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StatusSolicitudesRepository statusSolicitudesRepository;
    
    @Transactional
    public List<RequestDTO> findAll() {
    // Obtener todas las entidades Request
    List<Request> requests = requestRepository.findAll();

    // Convertir cada entidad Request a un DTO y devolver la lista de DTOs
    return requests.stream().map(request -> {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setDateRequest(request.getDateRequest());
        dto.setComment(request.getComment());
        dto.setDateStart(request.getDateStart());
        dto.setDateFinal(request.getDateFinal());
        dto.setEmployeeId(request.getIdEmployees().getId()); // Obtener el ID del empleado
        dto.setStatusCode(request.getStatusCode().getStatusCode()); // Obtener el código de estado
        dto.setIdTipeRequest(request.getIdTipeRequest().getId()); // Obtener el ID del tipo de solicitud
        
        // Convertir la lista de seguimientos (SeguimientosSolicitudes -> SeguimientoSolicitudDTO)
        List<SeguimientoSolicitudDTO> seguimientosDTO = request.getSeguimientosSolicitudesCollection()
            .stream()
            .map(seguimiento -> {
                SeguimientoSolicitudDTO seguimientoDTO = new SeguimientoSolicitudDTO();
                seguimientoDTO.setIdGuid(seguimiento.getIdGuid());
                seguimientoDTO.setVigente(seguimiento.getVigente());
                seguimientoDTO.setCreationDate(seguimiento.getCreationDate());
                seguimientoDTO.setStatusCode(seguimiento.getStatusCode().getStatusCode()); // Obtener el código de estado del seguimiento
                return seguimientoDTO;
            }).collect(Collectors.toList());

        // Asignar la lista de seguimientos al DTO
        dto.setSeguimientos(seguimientosDTO);
        
        return dto;
    }).collect(Collectors.toList());
    }   
    @Transactional
    public RequestDTO getRequestWithSeguimientos(Integer id) {
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        // Convertir la entidad Request a RequestDTO
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setDateRequest(request.getDateRequest());
        requestDTO.setComment(request.getComment());
        requestDTO.setDateStart(request.getDateStart());
        requestDTO.setDateFinal(request.getDateFinal());
        requestDTO.setEmployeeId(request.getIdEmployees().getId());
        requestDTO.setStatusCode(request.getStatusCode().getStatusCode());
        requestDTO.setIdTipeRequest(request.getIdTipeRequest().getId());

        // Convertir la colección de seguimientos a DTOs
        List<SeguimientoSolicitudDTO> seguimientosDTO = request.getSeguimientosSolicitudesCollection()
            .stream()
            .map(seguimiento -> {
                SeguimientoSolicitudDTO seguimientoDTO = new SeguimientoSolicitudDTO();
                seguimientoDTO.setIdGuid(seguimiento.getIdGuid());
                seguimientoDTO.setVigente(seguimiento.getVigente());
                seguimientoDTO.setCreationDate(seguimiento.getCreationDate());
                seguimientoDTO.setStatusCode(seguimiento.getStatusCode().getStatusCode());
                return seguimientoDTO;
            })
            .collect(Collectors.toList());

        requestDTO.setSeguimientos(seguimientosDTO);

        return requestDTO;
    }

    @Transactional
    public void updateRequest(Integer id, RequestDTO requestDTO) {
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setDateRequest(requestDTO.getDateRequest());
        request.setComment(requestDTO.getComment());
        request.setDateStart(requestDTO.getDateStart());
        request.setDateFinal(requestDTO.getDateFinal());

        requestRepository.save(request);
    }
    @Transactional
    public void createRequest(RequestDTO requestDTO) {
        // Crear el objeto Request
        Request request = new Request();
        request.setDateRequest(requestDTO.getDateRequest());
        request.setComment(requestDTO.getComment());
        request.setDateStart(requestDTO.getDateStart());
        request.setDateFinal(requestDTO.getDateFinal());
        request.setIdEmployees(new Employees(requestDTO.getEmployeeId()));  // Asignar empleado
        request.setStatusCode(new StatusSolicitudes(requestDTO.getStatusCode()));  // Asignar estado
        request.setIdTipeRequest(new TypeRequest(requestDTO.getIdTipeRequest()));  // Asignar tipo de solicitud
        
        // Guardar el objeto Request
        requestRepository.save(request);

        // Crear el objeto SeguimientosSolicitudes
        SeguimientosSolicitudes seguimiento = new SeguimientosSolicitudes();
        seguimiento.setIdGuid(UUID.randomUUID().toString());  // Generar un GUID
        seguimiento.setVigente(true);
        seguimiento.setCreationDate(new Date());
        seguimiento.setStatusCode(new StatusSolicitudes(1));  // Estado inicial

        // Relacionar el seguimiento con la solicitud creada
        seguimiento.setIdRequest(request);

        // Guardar el seguimiento
        seguimientoSolicitudRepository.save(seguimiento);
    }
    /*
    @Transactional
    public void createRequest(RequestDTO requestDto) {
    // Validar y obtener los objetos necesarios (empleado, tipo de solicitud, estado)
    TypeRequest typeRequest = TypeRequestRepository.findById(requestDto.getIdTipeRequest())
        .orElseThrow(() -> new EntityNotFoundException("Tipo de solicitud no encontrado"));

    Employees employees = employeeRepository.findById(requestDto.getEmployeeId())
        .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

    StatusSolicitudes status = statusSolicitudesRepository.findById(requestDto.getStatusCode())
        .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));

    // Crear y configurar la entidad Request
    Request request = new Request();
    request.setDateRequest(new Date());
    request.setComment(requestDto.getComment());
    request.setDateStart(requestDto.getDateStart());
    request.setDateFinal(requestDto.getDateFinal());
    request.setIdEmployees(employees);
    request.setStatusCode(status);
    request.setIdTipeRequest(typeRequest);

    // Guardar la solicitud
    requestRepository.save(request);
}*/

}


/*package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;
import com.GRH.myapp.GRH_myapp.model.TypeRequest;
import com.GRH.myapp.GRH_myapp.repository.RequestRepository;
import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import com.GRH.myapp.GRH_myapp.repository.TypeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TypeRequestRepository typeRequestRepository;

    @Autowired
    private SeguimientosSolicitudesRepository seguimientosSolicitudesRepository;

    // Obtener todos los registros
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    // Obtener un request por ID
    public Optional<Request> findById(Integer id) {
        return requestRepository.findById(id);
    }

    // Crear o actualizar un request
    public Request save(Request request) throws Exception {
        // Validar el límite de días según el tipo de solicitud
        TypeRequest typeRequest = typeRequestRepository.findById(request.getIdTipeRequest().getId())
                .orElseThrow(() -> new Exception("Tipo de solicitud no encontrado."));

        // Calcular la fecha límite
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getDateRequest());
        calendar.add(Calendar.DAY_OF_MONTH, typeRequest.getDias());

        // Comparar con la fecha actual
        if (new Date().after(calendar.getTime())) {
            throw new Exception("La solicitud debe hacerse con al menos " + typeRequest.getDias() + " días de anticipación.");
        }

        // Guardar la solicitud
        Request savedRequest = requestRepository.save(request);

        // Crear un seguimiento de la solicitud
        createSeguimiento(savedRequest, request.getStatusCode());

        return savedRequest; // Retorna la solicitud guardada
    }
    /*Crear una Solicitud y Registrar en Seguimientos
    Ya tienes el código para crear una solicitud y registrar su seguimiento. Vamos
    a asegurarnos de que al guardar una solicitud, también se verifiquen las restricciones 
    relacionadas con los tipos de solicitud.*/

    // Cambiar el estado de una solicitud
    /*public Request updateRequestStatus(Integer id, StatusSolicitudes newStatus) throws Exception {
        Optional<Request> optionalRequest = requestRepository.findById(id);

        if (!optionalRequest.isPresent()) {
            throw new Exception("Solicitud no encontrada.");
        }

        Request existingRequest = optionalRequest.get();

        // No se puede actualizar si el estado actual es rechazado
        if (existingRequest.getStatusCode().getStatusCode() == 0) { // Asumiendo 0 es el código para rechazado
            throw new Exception("No se puede actualizar el estado, la solicitud está rechazada.");
        }

        // Actualizar el estado de la solicitud
        existingRequest.setStatusCode(newStatus);
        Request updatedRequest = requestRepository.save(existingRequest);

        // Crear un seguimiento de la actualización del estado
        createSeguimiento(updatedRequest, newStatus);

        return updatedRequest; // Retorna la solicitud actualizada
    }
    /*. Cambiar el Estado y Crear un Nuevo Registro en Seguimientos
    Agregaremos un nuevo método para actualizar el estado de una solicitud
    y registrar cada cambio en la tabla de seguimientos.
    3. Restringir Cambios en Estado Rechazado
    Implementaremos la lógica para evitar cambios en el estado si la solicitud ha sido rechazada.*/

    /*private void createSeguimiento(Request request, StatusSolicitudes statusCode) {
        SeguimientosSolicitudes seguimiento = new SeguimientosSolicitudes();
        seguimiento.setIdRequest(request); // Asigna la solicitud guardada
        seguimiento.setStatusCode(statusCode); // Asigna el código de estado
        seguimiento.setVigente(true); // Marca como vigente
        seguimiento.setCreationDate(new Date()); // Asigna la fecha actual como fecha de creación

        // Guardar el seguimiento en la base de datos
        seguimientosSolicitudesRepository.save(seguimiento);
    }

    // Eliminar un request por ID
    public void deleteById(Integer id) {
        requestRepository.deleteById(id);
    }
}*/
