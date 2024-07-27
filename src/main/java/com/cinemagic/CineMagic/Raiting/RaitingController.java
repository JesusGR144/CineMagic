package com.cinemagic.cinemagic.raiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/raitings")
public class RaitingController {

    @Autowired
    private RaitingService raitingService;

    @GetMapping
    public List<Raiting> getAllRaitings() {
        return raitingService.getAllRaitings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raiting> getRaitingById(@PathVariable Long id) {
        Optional<Raiting> raiting = raitingService.getRaitingById(id);
        return raiting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Raiting createRaiting(@RequestBody Raiting raiting) {
        return raitingService.createRaiting(raiting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raiting> updateRaiting(@PathVariable Long id, @RequestBody Raiting raitingDetails) {
        try {
            Raiting updatedRaiting = raitingService.updateRaiting(id, raitingDetails);
            return ResponseEntity.ok(updatedRaiting);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaiting(@PathVariable Long id) {
        raitingService.deleteRaiting(id);
        return ResponseEntity.noContent().build();
    }
}
