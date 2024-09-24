package com.GRH.myapp.GRH_myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.repository.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    // Obtener todos los registros
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    // Obtener un request por ID
    public Optional<Request> findById(Integer id) {
        return requestRepository.findById(id);
    }

    // Crear o actualizar un request
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    // Eliminar un request por ID
    public void deleteById(Integer id) {
        requestRepository.deleteById(id);
    }
}
