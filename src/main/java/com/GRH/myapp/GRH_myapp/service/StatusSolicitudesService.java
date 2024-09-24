<<<<<<< HEAD
package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.StatusSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusSolicitudesService {

    @Autowired
    private StatusSolicitudesRepository statusSolicitudesRepository;

    public List<StatusSolicitudes> findAll() {
        return statusSolicitudesRepository.findAll();
    }

    public StatusSolicitudes save(StatusSolicitudes status) {
        return statusSolicitudesRepository.save(status);
    }
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.service;

/**
 *
 * @author cacri
 */
public class StatusSolicitudesService {
    
>>>>>>> main
}
