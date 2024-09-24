package com.GRH.myapp.GRH_myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.service.RequestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // Obtener todos los requests
    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.findAll();
    }

    // Obtener un request por ID
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id) {
        Optional<Request> request = requestService.findById(id);
        return request.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo request
    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        return requestService.save(request);
    }

    // Actualizar un request existente
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Integer id, @RequestBody Request request) {
        if (!requestService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        request.setId(id);
        Request updatedRequest = requestService.save(request);
        return ResponseEntity.ok(updatedRequest);
    }

    // Eliminar un request por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id) {
        if (!requestService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        requestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
