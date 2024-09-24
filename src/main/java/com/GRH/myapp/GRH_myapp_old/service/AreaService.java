/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cacri
 */
package com.GRH.myapp.GRH_myapp.service;

// todas las condiciones antes de aplicar un serve 
import com.GRH.myapp.GRH_myapp.model.Area;
import com.GRH.myapp.GRH_myapp.repository.AreaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    public Optional<Area> findById(Integer id) {
        return areaRepository.findById(id);
    }

    public Area save(Area area) {
        return areaRepository.save(area);
    }

    public void deleteById(Integer id) {
        areaRepository.deleteById(id);
    }   
}
