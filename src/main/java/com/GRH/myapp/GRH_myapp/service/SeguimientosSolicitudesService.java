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
}
