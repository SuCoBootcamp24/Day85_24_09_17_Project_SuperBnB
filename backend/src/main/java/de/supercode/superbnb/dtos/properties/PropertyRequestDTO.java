package de.supercode.superbnb.dtos.properties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PropertyRequestDTO(

        @NotBlank
        String name,

        String description,

        Integer guestsCapacity,

        Integer rooms,

        Integer beds,

        Integer bathrooms,

        Float rating,

        @DecimalMin(value = "0.0", message = "the Price must positive")
        BigDecimal priceAtNight,

        Boolean available,

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
