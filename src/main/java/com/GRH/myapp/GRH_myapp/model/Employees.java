/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GRH.myapp.GRH_myapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author cacri
 */
@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByNit", query = "SELECT e FROM Employees e WHERE e.nit = :nit"),
    @NamedQuery(name = "Employees.findByName", query = "SELECT e FROM Employees e WHERE e.name = :name"),
    @NamedQuery(name = "Employees.findByFirstSurname", query = "SELECT e FROM Employees e WHERE e.firstSurname = :firstSurname"),
    @NamedQuery(name = "Employees.findBySecondSurname", query = "SELECT e FROM Employees e WHERE e.secondSurname = :secondSurname"),
    @NamedQuery(name = "Employees.findByFirstLevel", query = "SELECT e FROM Employees e WHERE e.firstLevel = :firstLevel"),
    @NamedQuery(name = "Employees.findBySecondLevel", query = "SELECT e FROM Employees e WHERE e.secondLevel = :secondLevel"),
    @NamedQuery(name = "Employees.findByEntryDate", query = "SELECT e FROM Employees e WHERE e.entryDate = :entryDate"),
    @NamedQuery(name = "Employees.findByDelete", query = "SELECT e FROM Employees e WHERE e.delete = :delete"),
    @NamedQuery(name = "Employees.findByTurn", query = "SELECT e FROM Employees e WHERE e.turn = :turn"),
    @NamedQuery(name = "Employees.findBySchedule", query = "SELECT e FROM Employees e WHERE e.schedule = :schedule"),
    @NamedQuery(name = "Employees.findByRestDay", query = "SELECT e FROM Employees e WHERE e.restDay = :restDay")})
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NIT")
    private String nit;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "first_surname")
    private String firstSurname;
    @Basic(optional = false)
    @Column(name = "second_surname")
    private String secondSurname;
    @Basic(optional = false)
    @Column(name = "first_level")
    private String firstLevel;
    @Basic(optional = false)
    @Column(name = "second_level")
    private String secondLevel;
    @Basic(optional = false)
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Basic(optional = false)
    @Column(name = "delete")
    private String delete;
    @Basic(optional = false)
    @Column(name = "turn")
    private String turn;
    @Basic(optional = false)
    @Column(name = "schedule")
    private String schedule;
    @Column(name = "rest_day")
    private Integer restDay;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployees")
    private Collection<Request> requestCollection;
    @OneToMany(mappedBy = "idEmploye")
    private Collection<HolydaysPe> holydaysPeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployees")
    private Collection<Holydays> holydaysCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployees")
    private Collection<Payroll> payrollCollection;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_departament", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department idDepartament;
    @JoinColumn(name = "id_position", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Position idPosition;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUser;
    @JoinColumn(name = "id_workcenter", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkCenter idWorkcenter;

    public Employees() {
    }

    public Employees(Integer id) {
        this.id = id;
    }

    public Employees(Integer id, String nit, String name, String firstSurname, String secondSurname, String firstLevel, String secondLevel, Date entryDate, String delete, String turn, String schedule) {
        this.id = id;
        this.nit = nit;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.firstLevel = firstLevel;
        this.secondLevel = secondLevel;
        this.entryDate = entryDate;
        this.delete = delete;
        this.turn = turn;
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel;
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getRestDay() {
        return restDay;
    }

    public void setRestDay(Integer restDay) {
        this.restDay = restDay;
    }

    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    public Collection<HolydaysPe> getHolydaysPeCollection() {
        return holydaysPeCollection;
    }

    public void setHolydaysPeCollection(Collection<HolydaysPe> holydaysPeCollection) {
        this.holydaysPeCollection = holydaysPeCollection;
    }

    public Collection<Holydays> getHolydaysCollection() {
        return holydaysCollection;
    }

    public void setHolydaysCollection(Collection<Holydays> holydaysCollection) {
        this.holydaysCollection = holydaysCollection;
    }

    public Collection<Payroll> getPayrollCollection() {
        return payrollCollection;
    }

    public void setPayrollCollection(Collection<Payroll> payrollCollection) {
        this.payrollCollection = payrollCollection;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Department getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(Department idDepartament) {
        this.idDepartament = idDepartament;
    }

    public Position getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Position idPosition) {
        this.idPosition = idPosition;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public WorkCenter getIdWorkcenter() {
        return idWorkcenter;
    }

    public void setIdWorkcenter(WorkCenter idWorkcenter) {
        this.idWorkcenter = idWorkcenter;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.GRH.myapp.GRH_myapp.model.Employees[ id=" + id + " ]";
    }
    
}
