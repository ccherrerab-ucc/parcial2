package com.GRH.myapp.GRH_myapp.repository;


import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import java.util.List; // Import para List
import java.util.Optional;


@Repository
public interface SeguimientosSolicitudesRepository extends JpaRepository<SeguimientosSolicitudes, String> {
    @Override
    List<SeguimientosSolicitudes> findAll(Sort sort);
    
    Optional<SeguimientosSolicitudes> findTopByIdRequestOrderByCreationDateDesc(Request request);
    

}
