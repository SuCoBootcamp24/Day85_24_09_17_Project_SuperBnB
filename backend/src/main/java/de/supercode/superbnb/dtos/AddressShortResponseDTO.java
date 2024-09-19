package de.supercode.superbnb.dtos;

public record AddressShortResponseDTO(
        String city,
        String postalCode,

        String country
) {
}
