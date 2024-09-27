package com.GRH.myapp.GRH_myapp.controller;

import DTO.RequestDTO;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;
import com.GRH.myapp.GRH_myapp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;
    
    @GetMapping
    public List<RequestDTO> getAll() {
        return requestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestWithSeguimientos(@PathVariable Integer id) {
        RequestDTO requestDTO = requestService.getRequestWithSeguimientos(id);
        return ResponseEntity.ok(requestDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody RequestDTO requestDTO) {
        requestService.createRequest(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRequest(@PathVariable Integer id, @RequestBody RequestDTO requestDTO) {
        requestService.updateRequest(id, requestDTO);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/ruta")
    public ResponseEntity<String> manejarPost(@RequestBody RequestDTO solicitud) {
        // Lógica para manejar la solicitud POST
        return ResponseEntity.ok("Solicitud recibida");
    }
}
