package com.GRH.myapp.GRH_myapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity(name = "Seguimiento_Solicitude")
@Table(name = "seguimiento_solicitudes", indexes = {
        @Index(name = "status_code", columnList = "status_code")
})
public class SeguimientoSolicitude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guid", nullable = false)
    private Integer id;

    @Column(name = "id_request", nullable = false)
    private Integer idRequest;

    @Column(name = "vigente", nullable = false)
    private Boolean vigente = false;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creation_date")
    private Instant creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

}