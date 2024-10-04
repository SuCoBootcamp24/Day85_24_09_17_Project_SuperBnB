package de.supercode.superbnb.dtos.address;

public record AddressShortResponseDTO(
        String city,
        String zipCode,

        String country
) {
}
