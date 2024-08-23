package com.cinemagic.cinemagic.ticket;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketDTO {

    @NotBlank(message = "El asiento es obligatorio.")
    private String seat;

    @NotBlank(message = "El estado del ticket es obligatorio.")
    private String state;

    private Boolean isActive;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long userId;

    @NotNull(message = "El ID de la función es obligatorio.")
    private Long scheduleId;
}
