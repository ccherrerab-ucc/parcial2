package com.GRH.myapp.GRH_myapp.repository;

import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusSolicitudesRepository extends JpaRepository<StatusSolicitudes, Integer> {
    Optional<StatusSolicitudes> findByStatusCode(int statusCode);
}

