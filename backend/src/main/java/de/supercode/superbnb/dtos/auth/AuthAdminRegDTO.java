package de.supercode.superbnb.dtos.auth;

import de.supercode.superbnb.entities.person.Role;
import jakarta.validation.constraints.NotBlank;

public record AuthAdminRegDTO(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String email,

        @NotBlank
        String password,

        String phone,

        Role role
) {
}
