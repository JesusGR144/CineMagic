package com.cinemagic.cinemagic.raiting;

import com.cinemagic.cinemagic.movie.Movie;
import com.cinemagic.cinemagic.user.User;

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

public class Raiting {
    @Id
    @SequenceGenerator(
        name = "raiting_sequence",
        sequenceName = "raiting_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "raiting_sequence")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    private int score;
    private String comment;
}
