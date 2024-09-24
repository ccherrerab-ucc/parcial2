package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.Area;
import com.GRH.myapp.GRH_myapp.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<Area> getAllAreas() {
        return areaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable Integer id) {
        Optional<Area> area = areaService.findById(id);
        return area.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaService.save(area);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable Integer id, @RequestBody Area area) {
        if (!areaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        area.setId(id);
        Area updatedArea = areaService.save(area);
        return ResponseEntity.ok(updatedArea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Integer id) {
        if (!areaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        areaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
