package de.supercode.superbnb.dtos.user;

import de.supercode.superbnb.entities.person.Role;

public record UserResponseDTO(

        long id,
        String firstName,
        String lastName,

        Role role
) {
}
