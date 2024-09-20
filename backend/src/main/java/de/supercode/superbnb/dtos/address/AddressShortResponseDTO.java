package de.supercode.superbnb.dtos.address;

public record AddressShortResponseDTO(
        String city,
        String postalCode,

        String country
) {
}
