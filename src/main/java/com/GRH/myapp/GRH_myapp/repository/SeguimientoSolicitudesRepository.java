package com.GRH.myapp.GRH_myapp.repository;


import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoSolicitudesRepository extends JpaRepository<SeguimientoSolicitudes, Integer> {

    List<SeguimientoSolicitudes> findByVigenteTrue();
    SeguimientoSolicitudes findByIdQuick(Integer idQuick);

}
