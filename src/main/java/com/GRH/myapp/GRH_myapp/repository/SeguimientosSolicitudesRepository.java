package com.GRH.myapp.GRH_myapp.repository;

import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientosSolicitudesRepository extends JpaRepository<SeguimientosSolicitudes, Integer> {
}
