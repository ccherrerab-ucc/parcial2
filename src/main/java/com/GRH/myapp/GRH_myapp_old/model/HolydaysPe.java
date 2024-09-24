/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

/**
 *
 * @author cacri
 */
@Entity
@Table(name = "holydays_pe")
@NamedQueries({
    @NamedQuery(name = "HolydaysPe.findAll", query = "SELECT h FROM HolydaysPe h"),
    @NamedQuery(name = "HolydaysPe.findById", query = "SELECT h FROM HolydaysPe h WHERE h.id = :id"),
    @NamedQuery(name = "HolydaysPe.findByHolydayPeriod", query = "SELECT h FROM HolydaysPe h WHERE h.holydayPeriod = :holydayPeriod"),
    @NamedQuery(name = "HolydaysPe.findByCorrespondDays", query = "SELECT h FROM HolydaysPe h WHERE h.correspondDays = :correspondDays"),
    @NamedQuery(name = "HolydaysPe.findByTakenDays", query = "SELECT h FROM HolydaysPe h WHERE h.takenDays = :takenDays"),
    @NamedQuery(name = "HolydaysPe.findByPendingDays", query = "SELECT h FROM HolydaysPe h WHERE h.pendingDays = :pendingDays"),
    @NamedQuery(name = "HolydaysPe.findByDatecreate", query = "SELECT h FROM HolydaysPe h WHERE h.datecreate = :datecreate"),
    @NamedQuery(name = "HolydaysPe.findByDateupdate", query = "SELECT h FROM HolydaysPe h WHERE h.dateupdate = :dateupdate")})
public class HolydaysPe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "holyday_period")
    private Integer holydayPeriod;
    @Column(name = "correspond_days")
    private Integer correspondDays;
    @Column(name = "taken_days")
    private Integer takenDays;
    @Column(name = "pending_days")
    private Integer pendingDays;
    @Column(name = "datecreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    @Column(name = "dateupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateupdate;
    @JoinColumn(name = "id_employe", referencedColumnName = "id")
    @ManyToOne
    private Employees idEmploye;

    public HolydaysPe() {
    }

    public HolydaysPe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHolydayPeriod() {
        return holydayPeriod;
    }

    public void setHolydayPeriod(Integer holydayPeriod) {
        this.holydayPeriod = holydayPeriod;
    }

    public Integer getCorrespondDays() {
        return correspondDays;
    }

    public void setCorrespondDays(Integer correspondDays) {
        this.correspondDays = correspondDays;
    }

    public Integer getTakenDays() {
        return takenDays;
    }

    public void setTakenDays(Integer takenDays) {
        this.takenDays = takenDays;
    }

    public Integer getPendingDays() {
        return pendingDays;
    }

    public void setPendingDays(Integer pendingDays) {
        this.pendingDays = pendingDays;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Date getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
    }

    public Employees getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employees idEmploye) {
        this.idEmploye = idEmploye;
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
        if (!(object instanceof HolydaysPe)) {
            return false;
        }
        HolydaysPe other = (HolydaysPe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.HolydaysPe[ id=" + id + " ]";
    }
    
}
