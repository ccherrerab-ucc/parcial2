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
    private SeguimientosSolicitudesService seguimientosSolicitudesService;
       
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
        // Paso 1: Encontrar y actualizar el registro de Request
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setDateRequest(requestDTO.getDateRequest());
        request.setComment(requestDTO.getComment());
        request.setDateStart(requestDTO.getDateStart());
        request.setDateFinal(requestDTO.getDateFinal());

        // Guardar los cambios en Request
        requestRepository.save(request);

        // Paso 2: Actualizar los seguimientos asociados a esta solicitud
        seguimientosSolicitudesService.handleSeguimientosForRequestUpdate(request);
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
   
}

