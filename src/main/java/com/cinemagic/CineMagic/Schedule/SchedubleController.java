package com.cinemagic.cinemagic.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedubles")
public class SchedubleController {

    @Autowired
    private SchedubleService schedubleService;

    @GetMapping
    public List<Scheduble> getAllSchedubles() {
        return schedubleService.getAllSchedubles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scheduble> getSchedubleById(@PathVariable Long id) {
        Optional<Scheduble> scheduble = schedubleService.getSchedubleById(id);
        return scheduble.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Scheduble createScheduble(@RequestBody Scheduble scheduble) {
        return schedubleService.createScheduble(scheduble);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scheduble> updateScheduble(@PathVariable Long id, @RequestBody Scheduble schedubleDetails) {
        try {
            Scheduble updatedScheduble = schedubleService.updateScheduble(id, schedubleDetails);
            return ResponseEntity.ok(updatedScheduble);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduble(@PathVariable Long id) {
        schedubleService.deleteScheduble(id);
        return ResponseEntity.noContent().build();
    }
}
