/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "type_request")
@NamedQueries({
    @NamedQuery(name = "TypeRequest.findAll", query = "SELECT t FROM TypeRequest t"),
    @NamedQuery(name = "TypeRequest.findById", query = "SELECT t FROM TypeRequest t WHERE t.id = :id"),
    @NamedQuery(name = "TypeRequest.findByNombre", query = "SELECT t FROM TypeRequest t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TypeRequest.findByD\u00edas", query = "SELECT t FROM TypeRequest t WHERE t.d\u00edas = :d\u00edas")})
public class TypeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "d\u00edas")
    private Integer días;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipeRequest")
    private Collection<Request> requestCollection;

    public TypeRequest() {
    }

    public TypeRequest(Integer id) {
        this.id = id;
    }

    public TypeRequest(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDías() {
        return días;
    }

    public void setDías(Integer días) {
        this.días = días;
    }

    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeRequest)) {
            return false;
        }
        TypeRequest other = (TypeRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.TypeRequest[ id=" + id + " ]";
    }

    public int getDias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
