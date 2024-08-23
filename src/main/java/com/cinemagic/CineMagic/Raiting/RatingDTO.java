package com.cinemagic.cinemagic.raiting;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingDTO {

    @NotNull(message = "La puntuación es obligatoria.")
    @Min(value = 1, message = "La puntuación mínima es 1.")
    @Max(value = 5, message = "La puntuación máxima es 5.")
    private Float score;

    private String comment;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long userId;

    @NotNull(message = "El ID de la película es obligatorio.")
    private Long movieId;
}

