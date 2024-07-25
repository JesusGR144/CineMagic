package com.cinemagic.CineMagic.Schedule;

import com.cinemagic.CineMagic.Movie.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Scheduble {
    @Id
    @SequenceGenerator(
        name = "scheduble_sequence",
        sequenceName = "scheduble_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "scheduble_sequence")
    private Long id;

    @ManyToOne
    private Movie movie;

    private String dateTime;
    private String cinemaRoom;
}
