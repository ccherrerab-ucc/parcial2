package com.GRH.myapp.GRH_myapp;
//spring.datasource.url= jdbc:mariadb://localhost:3306/csuc_grh spring.datasource.url= jdbc:mysql://localhost:3306/csuc_grh

import com.GRH.myapp.GRH_myapp.model.Area;
import com.GRH.myapp.GRH_myapp.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrhMyappApplication implements CommandLineRunner {

    @Autowired
    private AreaService areaService;

    public static void main(String[] args) {
        SpringApplication.run(GrhMyappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear y guardar un nuevo registro en la base de datos
        Area area = new Area();
        area.setNameArea("OTI");
        areaService.save(area);

        // Opcional: Imprimir todos los registros para verificar
        areaService.findAll().forEach(System.out::println);
    }
}
