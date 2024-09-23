package de.supercode.superbnb.dtos.properties;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PropertyRequestDTO(

        @NotBlank
        String name,

        String description,

        int guestsCapacity,

        int rooms,

        int beds,

        int bathrooms,

        double rating,

        @NotBlank
        BigDecimal priceAtNight,

        boolean available,

        @NotBlank
        String street,

        @NotBlank
        String houseNumber,

        @NotBlank
        String zipCode,

        @NotBlank
        String city,

        @NotBlank
        String country
) {
}
