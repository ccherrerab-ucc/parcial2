/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.TypeRequest;
import com.GRH.myapp.GRH_myapp.repository.TypeRequestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cacri
 */
@Service

public class TypeRequestService {
   

    @Autowired
    private TypeRequestRepository typeRequestRepository;

    public List<TypeRequest> findAll() {
        return typeRequestRepository.findAll();
    }

    public TypeRequest save(TypeRequest TypeRequest) {
        return typeRequestRepository.save(TypeRequest);
    }
    public Optional<TypeRequest> findById(Integer id) {
        return typeRequestRepository.findById(id);
    }
    
    
}


