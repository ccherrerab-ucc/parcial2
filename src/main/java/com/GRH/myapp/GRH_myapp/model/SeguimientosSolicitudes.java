/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.PrePersist;

/**
 *
 * @author cacri
 */
@Entity
@Table(name = "seguimientos_solicitudes")
@NamedQueries({
    @NamedQuery(name = "SeguimientosSolicitudes.findAll", query = "SELECT s FROM SeguimientosSolicitudes s"),
    @NamedQuery(name = "SeguimientosSolicitudes.findByIdGuid", query = "SELECT s FROM SeguimientosSolicitudes s WHERE s.idGuid = :idGuid"),
    @NamedQuery(name = "SeguimientosSolicitudes.findByVigente", query = "SELECT s FROM SeguimientosSolicitudes s WHERE s.vigente = :vigente"),
    @NamedQuery(name = "SeguimientosSolicitudes.findByCreationDate", query = "SELECT s FROM SeguimientosSolicitudes s WHERE s.creationDate = :creationDate")})
public class SeguimientosSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_guid")
    private String idGuid;
    @Column(name = "vigente")
    private Boolean vigente;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "id_request", referencedColumnName = "id")
    @JsonBackReference
    @ManyToOne
    private Request idRequest;
    @JoinColumn(name = "status_code", referencedColumnName = "status_code")
    @JsonBackReference
    @ManyToOne
    private StatusSolicitudes statusCode;

    public SeguimientosSolicitudes() {
    }

    public SeguimientosSolicitudes(String idGuid) {
        this.idGuid = idGuid;
    }
    
    @PrePersist
    public void prePersist() {
        if (this.idGuid == null) {
            this.idGuid = UUID.randomUUID().toString();
        }
    }

    public String getIdGuid() {
        return idGuid;
    }

    public void setIdGuid(String idGuid) {
        this.idGuid = idGuid;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Request getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Request idRequest) {
        this.idRequest = idRequest;
    }

    public StatusSolicitudes getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusSolicitudes statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuid != null ? idGuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientosSolicitudes)) {
            return false;
        }
        SeguimientosSolicitudes other = (SeguimientosSolicitudes) object;
        if ((this.idGuid == null && other.idGuid != null) || (this.idGuid != null && !this.idGuid.equals(other.idGuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes[ idGuid=" + idGuid + " ]";
    }
    
}
