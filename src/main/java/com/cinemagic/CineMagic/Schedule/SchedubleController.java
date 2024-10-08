// package com.cinemagic.cinemagic.schedule;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/schedubles")
// public class SchedubleController {

//     @Autowired
//     private SchedubleService schedubleService;

//     @GetMapping
//     public List<Schedule> getAllSchedubles() {
//         return schedubleService.getAllSchedubles();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Schedule> getSchedubleById(@PathVariable Long id) {
//         Optional<Schedule> scheduble = schedubleService.getSchedubleById(id);
//         return scheduble.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public Schedule createScheduble(@RequestBody Schedule scheduble) {
//         return schedubleService.createScheduble(scheduble);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Schedule> updateScheduble(@PathVariable Long id, @RequestBody Schedule schedubleDetails) {
//         try {
//             Schedule updatedScheduble = schedubleService.updateScheduble(id, schedubleDetails);
//             return ResponseEntity.ok(updatedScheduble);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteScheduble(@PathVariable Long id) {
//         schedubleService.deleteScheduble(id);
//         return ResponseEntity.noContent().build();
//     }
// }


package com.cinemagic.cinemagic.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedubles")
@Validated
public class SchedubleController {

    @Autowired
    private SchedubleService schedubleService;

    @GetMapping
    public List<Schedule> getAllSchedubles() {
        return schedubleService.getAllSchedubles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedubleById(@PathVariable Long id) {
        Optional<Schedule> scheduble = schedubleService.getSchedubleById(id);
        return scheduble.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Schedule> createScheduble(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        Schedule createdScheduble = schedubleService.createScheduble(scheduleDTO);
        return ResponseEntity.ok(createdScheduble);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateScheduble(@PathVariable Long id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule updatedScheduble = schedubleService.updateScheduble(id, scheduleDTO);
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
