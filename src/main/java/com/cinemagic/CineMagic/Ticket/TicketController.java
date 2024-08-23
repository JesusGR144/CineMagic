// package com.cinemagic.cinemagic.ticket;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/tickets")
// public class TicketController {

//     @Autowired
//     private TicketService ticketService;

//     @GetMapping
//     public List<Ticket> getAllTickets() {
//         return ticketService.getAllTickets();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
//         Optional<Ticket> ticket = ticketService.getTicketById(id);
//         return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public Ticket createTicket(@RequestBody Ticket ticket) {
//         return ticketService.createTicket(ticket);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
//         try {
//             Ticket updatedTicket = ticketService.updateTicket(id, ticketDetails);
//             return ResponseEntity.ok(updatedTicket);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
//         ticketService.deleteTicket(id);
//         return ResponseEntity.noContent().build();
//     }
// }


package com.cinemagic.cinemagic.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        Optional<TicketDTO> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDTO ticketDTO) {
        try {
            TicketDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
            return ResponseEntity.ok(updatedTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
