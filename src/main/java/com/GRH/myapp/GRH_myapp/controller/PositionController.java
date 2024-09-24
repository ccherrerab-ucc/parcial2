/*package com.GRH.myapp.GRH_myapp.controller;

import com.GRH.myapp.GRH_myapp.model.Position;
import com.GRH.myapp.GRH_myapp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Positions")
public class PositionController {

    @Autowired
    private PositionService PositionService;

    @GetMapping
    public List<Position> getAllPositions() {
        return PositionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Integer id) {
        Optional<Position> Position = PositionService.findById(id);
        return Position.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Position createPosition(@RequestBody Position Position) {
        return PositionService.save(Position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Integer id, @RequestBody Position Position) {
        if (!PositionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Position.setId(id);
        Position updatedPosition = PositionService.save(Position);
        return ResponseEntity.ok(updatedPosition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Integer id) {
        if (!PositionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PositionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
*/