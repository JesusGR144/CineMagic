package com.cinemagic.cinemagic.movie;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieDTO {

    @NotBlank(message = "El título de la película es obligatorio.")
    private String title;

    @NotBlank(message = "La fecha de lanzamiento es obligatoria.")
    private String releaseDate;

    @NotNull(message = "El género de la película es obligatorio.")
    private Genre genre;
}
