/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.GRH.myapp.GRH_myapp.model.Employees;
import com.GRH.myapp.GRH_myapp.repository.EmployeeRepository;
import java.util.Optional;
import org.hibernate.Hibernate;


/**
 *
 * @author cacri
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Employees getEmployeeWithRequests(Integer id) {
        // Aquí puedes acceder a employee.getRequestCollection() sin problemas

        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Puedes agregar otros métodos relacionados con empleados aquí
}
