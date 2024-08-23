package com.cinemagic.cinemagic.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Email(message = "Invalid email")
    private String email;

    private boolean accountNotExpired;

    private boolean accountNotLocked;
}
