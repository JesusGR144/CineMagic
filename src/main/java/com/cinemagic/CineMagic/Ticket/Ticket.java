package com.cinemagic.CineMagic.Ticket;

import com.cinemagic.CineMagic.Schedule.Scheduble;
import com.cinemagic.CineMagic.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @SequenceGenerator(
        name = "ticket_sequence",
        sequenceName = "ticket_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "ticket_sequence")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Scheduble scheduble;

    private String seat;
    private String state;
}
