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
import java.io.Serializable;

/**
 *
 * @author cacri
 */
@Entity
@Table(name = "payroll")
@NamedQueries({
    @NamedQuery(name = "Payroll.findAll", query = "SELECT p FROM Payroll p"),
    @NamedQuery(name = "Payroll.findById", query = "SELECT p FROM Payroll p WHERE p.id = :id"),
    @NamedQuery(name = "Payroll.findByBasicSalary", query = "SELECT p FROM Payroll p WHERE p.basicSalary = :basicSalary"),
    @NamedQuery(name = "Payroll.findByBonus", query = "SELECT p FROM Payroll p WHERE p.bonus = :bonus"),
    @NamedQuery(name = "Payroll.findByNetBalance", query = "SELECT p FROM Payroll p WHERE p.netBalance = :netBalance")})
public class Payroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "basic_salary")
    private int basicSalary;
    @Basic(optional = false)
    @Column(name = "bonus")
    private int bonus;
    @Basic(optional = false)
    @Column(name = "net_balance")
    private int netBalance;
    @JoinColumn(name = "id_employees", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employees idEmployees;

    public Payroll() {
    }

    public Payroll(Integer id) {
        this.id = id;
    }

    public Payroll(Integer id, int basicSalary, int bonus, int netBalance) {
        this.id = id;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.netBalance = netBalance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(int netBalance) {
        this.netBalance = netBalance;
    }

    public Employees getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(Employees idEmployees) {
        this.idEmployees = idEmployees;
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
        if (!(object instanceof Payroll)) {
            return false;
        }
        Payroll other = (Payroll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.Payroll[ id=" + id + " ]";
    }
    
}
