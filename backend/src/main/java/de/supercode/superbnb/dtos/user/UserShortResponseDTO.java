package de.supercode.superbnb.dtos.user;

import de.supercode.superbnb.entities.person.Role;

public record UserShortResponseDTO(

        long id,
        String firstName,
        String lastName,

        Role role
) {
}
