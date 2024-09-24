/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.repository;

import com.GRH.myapp.GRH_myapp.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cacri
 */
public interface AreaRepository extends JpaRepository<Area, Integer> {

}
