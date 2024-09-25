package com.GRH.myapp.GRH_myapp.repository;


import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import java.util.List; // Import para List


@Repository
public interface SeguimientosSolicitudesRepository extends JpaRepository<SeguimientosSolicitudes, Integer> {
    List<SeguimientosSolicitudes> findAll(Sort sort);

}
