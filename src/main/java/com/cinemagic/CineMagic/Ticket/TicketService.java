// package com.cinemagic.cinemagic.ticket;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.cinemagic.cinemagic.exceptions.ApiRequestException;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class TicketService {

//     @Autowired
//     private TicketRepository ticketRepository;

//     public List<Ticket> getAllTickets() {
//         return ticketRepository.findAll();
//     }

//     public Optional<Ticket> getTicketById(Long id) {
//         return ticketRepository.findById(id);
//     }

//     public Ticket createTicket(Ticket ticket) {
//         return ticketRepository.save(ticket);
//     }

//     public Ticket updateTicket(Long id, Ticket ticketDetails) {
//         return ticketRepository.findById(id).map(ticket -> {
//             ticket.setUser(ticketDetails.getUser());
//             ticket.setScheduble(ticketDetails.getScheduble());
//             ticket.setSeat(ticketDetails.getSeat());
//             ticket.setState(ticketDetails.getState());
//             return ticketRepository.save(ticket);
//         }).orElseThrow(() -> new ApiRequestException("Ticket not found with id " + id));
//     }

//     public void deleteTicket(Long id) {
//         ticketRepository.deleteById(id);
//     }
// }


package com.cinemagic.cinemagic.ticket;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;
import com.cinemagic.cinemagic.schedule.SchedubleRepository;
import com.cinemagic.cinemagic.schedule.Schedule;
import com.cinemagic.cinemagic.user.UserEntity;
import com.cinemagic.cinemagic.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchedubleRepository scheduleRepository;

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TicketDTO> getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }

    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        if (!ticketRepository.existsById(id)) {
            throw new ApiRequestException("Ticket not found with id " + id);
        }
        Ticket updatedTicket = convertToEntity(ticketDTO);
        updatedTicket.setId(id);
        Ticket savedTicket = ticketRepository.save(updatedTicket);
        return convertToDTO(savedTicket);
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new ApiRequestException("Ticket not found with id " + id);
        }
        ticketRepository.deleteById(id);
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSeat(ticket.getSeat());
        ticketDTO.setState(ticket.getState());
        ticketDTO.setIsActive(ticket.isActive());
        ticketDTO.setUserId(ticket.getUser().getId());
        ticketDTO.setScheduleId(ticket.getScheduble().getId());
        return ticketDTO;
    }

    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setSeat(ticketDTO.getSeat());
        ticket.setState(ticketDTO.getState());
        ticket.setActive(ticketDTO.getIsActive());

        UserEntity user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new ApiRequestException("User not found with id " + ticketDTO.getUserId()));
        Schedule schedule = scheduleRepository.findById(ticketDTO.getScheduleId())
                .orElseThrow(() -> new ApiRequestException("Schedule not found with id " + ticketDTO.getScheduleId()));
        
        ticket.setUser(user);
        ticket.setScheduble(schedule);
        return ticket;
    }
}
