/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author cacri
 */
@Entity
@Table(name = "status_solicitudes")
@NamedQueries({
    @NamedQuery(name = "StatusSolicitudes.findAll", query = "SELECT s FROM StatusSolicitudes s"),
    @NamedQuery(name = "StatusSolicitudes.findByStatusCode", query = "SELECT s FROM StatusSolicitudes s WHERE s.statusCode = :statusCode"),
    @NamedQuery(name = "StatusSolicitudes.findByDescription", query = "SELECT s FROM StatusSolicitudes s WHERE s.description = :description")})
public class StatusSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "status_code")
    private Integer statusCode;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusCode")
    private Collection<SeguimientoSolicitudes> seguimientoSolicitudesCollection;

    public StatusSolicitudes() {
    }

    public StatusSolicitudes(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public StatusSolicitudes(Integer statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<SeguimientoSolicitudes> getSeguimientosSolicitudesCollection() {
        return seguimientoSolicitudesCollection;
    }

    public void setSeguimientosSolicitudesCollection(Collection<SeguimientoSolicitudes> seguimientoSolicitudesCollection) {
        this.seguimientoSolicitudesCollection = seguimientoSolicitudesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusCode != null ? statusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusSolicitudes)) {
            return false;
        }
        StatusSolicitudes other = (StatusSolicitudes) object;
        if ((this.statusCode == null && other.statusCode != null) || (this.statusCode != null && !this.statusCode.equals(other.statusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.StatusSolicitudes[ statusCode=" + statusCode + " ]";
    }
    
}
