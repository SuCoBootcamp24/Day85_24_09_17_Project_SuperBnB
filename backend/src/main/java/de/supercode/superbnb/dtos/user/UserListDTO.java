package de.supercode.superbnb.dtos.user;

public record UserListDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String role
) {
}
