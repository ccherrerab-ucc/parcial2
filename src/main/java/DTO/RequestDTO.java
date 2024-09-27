/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author cacri
 */
public class RequestDTO {
    private Integer id;
    private Date dateRequest;
    private String comment;
    private Date dateStart;
    private Date dateFinal;
    private Integer employeeId; // solo el ID para simplificar
    private Integer statusCode; // solo el ID para simplificar
    private Integer idTipeRequest; // solo el ID para simplificar
    private SeguimientoSolicitudDTO seguimiento;  // Agregar un campo para Seguimientos

    

    private List<SeguimientoSolicitudDTO> seguimientos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getIdTipeRequest() {
        return idTipeRequest;
    }

    public void setIdTipeRequest(Integer idTipeRequest) {
        this.idTipeRequest = idTipeRequest;
    }

    public List<SeguimientoSolicitudDTO> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<SeguimientoSolicitudDTO> seguimientos) {
        this.seguimientos = seguimientos;
    }
    
    public SeguimientoSolicitudDTO getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(SeguimientoSolicitudDTO seguimiento) {
        this.seguimiento = seguimiento;
    }
    
    
}
