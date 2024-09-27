/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "permission_role")
@NamedQueries({
    @NamedQuery(name = "PermissionRole.findAll", query = "SELECT p FROM PermissionRole p"),
    @NamedQuery(name = "PermissionRole.findById", query = "SELECT p FROM PermissionRole p WHERE p.id = :id"),
    @NamedQuery(name = "PermissionRole.findByNameRole", query = "SELECT p FROM PermissionRole p WHERE p.nameRole = :nameRole")})
public class PermissionRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_role")
    private String nameRole;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private Collection<Users> usersCollection;

    public PermissionRole() {
    }

    public PermissionRole(Integer id) {
        this.id = id;
    }

    public PermissionRole(Integer id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof PermissionRole)) {
            return false;
        }
        PermissionRole other = (PermissionRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.PermissionRole[ id=" + id + " ]";
    }
    
}
