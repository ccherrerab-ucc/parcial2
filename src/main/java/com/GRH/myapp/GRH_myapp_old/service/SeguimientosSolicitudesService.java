<<<<<<< HEAD
package com.GRH.myapp.GRH_myapp.service;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.repository.SeguimientosSolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class SeguimientosSolicitudesService {
    
>>>>>>> main
}
