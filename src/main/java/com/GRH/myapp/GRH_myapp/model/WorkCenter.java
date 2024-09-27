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
@Table(name = "work_center")
@NamedQueries({
    @NamedQuery(name = "WorkCenter.findAll", query = "SELECT w FROM WorkCenter w"),
    @NamedQuery(name = "WorkCenter.findById", query = "SELECT w FROM WorkCenter w WHERE w.id = :id"),
    @NamedQuery(name = "WorkCenter.findByNameCity", query = "SELECT w FROM WorkCenter w WHERE w.nameCity = :nameCity")})
public class WorkCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_city")
    private String nameCity;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorkcenter")
    private Collection<Employees> employeesCollection;

    public WorkCenter() {
    }

    public WorkCenter(Integer id) {
        this.id = id;
    }

    public WorkCenter(Integer id, String nameCity) {
        this.id = id;
        this.nameCity = nameCity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
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
        if (!(object instanceof WorkCenter)) {
            return false;
        }
        WorkCenter other = (WorkCenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.WorkCenter[ id=" + id + " ]";
    }
    
}
