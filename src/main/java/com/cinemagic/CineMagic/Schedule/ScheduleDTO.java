package com.cinemagic.cinemagic.schedule;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScheduleDTO {

    @NotNull(message = "La fecha y hora de la función son obligatorias.")    
    private String dateTime;

    @NotBlank(message = "La sala de cine es obligatoria.")
    private String cinemaRoom;

    @NotNull(message = "El ID de la película es obligatorio.")
    private Long movieId;
}

