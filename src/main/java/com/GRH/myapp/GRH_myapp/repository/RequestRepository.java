package com.GRH.myapp.GRH_myapp.repository;

import DTO.RequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import java.util.List;
import org.springframework.data.domain.Sort;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    
}
