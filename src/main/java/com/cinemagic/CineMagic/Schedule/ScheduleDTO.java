package com.cinemagic.cinemagic.schedule;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleDTO {

    @NotNull(message = "La fecha y hora de la función son obligatorias.")    
    private LocalDateTime dateTime;

    @NotBlank(message = "La sala de cine es obligatoria.")
    private String cinemaRoom;

    @NotNull(message = "El ID de la película es obligatorio.")
    private Long movieId;
}

