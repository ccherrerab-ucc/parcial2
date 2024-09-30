package com.GRH.myapp.GRH_myapp;

import com.GRH.myapp.GRH_myapp.model.SeguimientoSolicitudes;
import com.GRH.myapp.GRH_myapp.service.SeguimientoSolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GrhMyappApplication {

    @Autowired
    private SeguimientoSolicitudesService seguimientoSolicitudesService;

    public static void main(String[] args) {
        SpringApplication.run(GrhMyappApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // 1. Obtener todos los seguimientos
            List<SeguimientoSolicitudes> seguimientos = seguimientoSolicitudesService.getAll();
            System.out.println("Lista de Seguimientos: " + seguimientos);

            // 2. Crear un nuevo seguimiento
            SeguimientoSolicitudes nuevoSeguimiento = new SeguimientoSolicitudes();
            // Configura las propiedades del nuevo seguimiento aquí
            nuevoSeguimiento.setVigente(true);
            // Establece otros campos necesarios
            // nuevoSeguimiento.setOtroCampo(valor);
            seguimientoSolicitudesService.save(nuevoSeguimiento);
            System.out.println("Nuevo seguimiento guardado: " + nuevoSeguimiento);

            // 3. Actualizar el estado de un seguimiento existente
            try {
                SeguimientoSolicitudes updatedSeguimiento = seguimientoSolicitudesService.updateStatus(1, 2); // Cambia 1 por un ID válido
                System.out.println("Seguimiento actualizado: " + updatedSeguimiento);
            } catch (IllegalStateException e) {
                System.err.println("Error al actualizar el seguimiento: " + e.getMessage());
            }
        };
    }
}
