/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author cacri
 */
public class SeguimientoSolicitudDTO {
    private String idGuid;
    private Boolean vigente;
    private Date creationDate;
    private Integer statusCode; // solo el ID para simplificar

    // Getters y Setters

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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
}
