package com.GRH.myapp.GRH_myapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Status_Solicitude")
@Table(name = "status_solicitudes")
public class StatusSolicitude {
    @Id
    @Column(name = "status_code", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}