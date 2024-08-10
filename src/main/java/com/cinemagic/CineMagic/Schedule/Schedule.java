package com.cinemagic.cinemagic.schedule;

import com.cinemagic.cinemagic.movie.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Schedule {
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
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String dateTime;
    private String cinemaRoom;
}
