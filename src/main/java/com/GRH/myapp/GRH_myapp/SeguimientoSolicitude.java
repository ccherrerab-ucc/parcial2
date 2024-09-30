package com.GRH.myapp.GRH_myapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity(name = "Seguimiento_Solicitude")
@Table(name = "seguimiento_solicitudes", indexes = {
        @Index(name = "status_code", columnList = "status_code")
})
public class SeguimientoSolicitude {
}