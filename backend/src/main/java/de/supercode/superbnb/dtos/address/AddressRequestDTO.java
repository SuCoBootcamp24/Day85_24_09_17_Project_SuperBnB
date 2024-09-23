package de.supercode.superbnb.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank
        String street,

        @NotBlank
        String houseNumber,

        @NotBlank
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String country
) {
}