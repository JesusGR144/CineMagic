package com.cinemagic.cinemagic.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setUser(ticketDetails.getUser());
            ticket.setScheduble(ticketDetails.getScheduble());
            ticket.setSeat(ticketDetails.getSeat());
            ticket.setState(ticketDetails.getState());
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new ApiRequestException("Ticket not found with id " + id));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
