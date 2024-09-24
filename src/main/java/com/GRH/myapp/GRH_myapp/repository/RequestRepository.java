package com.GRH.myapp.GRH_myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.GRH.myapp.GRH_myapp.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    // Ya puedes usar métodos como save(), findById(), findAll(), deleteById(), etc.
}
