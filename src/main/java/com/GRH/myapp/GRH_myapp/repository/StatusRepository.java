package com.GRH.myapp.GRH_myapp.repository;

import com.GRH.myapp.GRH_myapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
