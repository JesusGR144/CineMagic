package com.cinemagic.cinemagic.movie;

import java.util.HashSet;
import java.util.Set;

import com.cinemagic.cinemagic.raiting.Raiting;
import com.cinemagic.cinemagic.schedule.Schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(
        name = "movie_sequence",
        sequenceName = "movie_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "movie_sequence")
    private Long id;

    private String title;
    // private String description;
    private String releaseDate;

    @OneToMany(mappedBy = "movie")
    private Set<Schedule> schedubles = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Raiting> raitings = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
