package de.supercode.superbnb.dtos;

import java.math.BigDecimal;

public record PropertyResponseDTO(
        Long id,
        String title,
        String description,
        BigDecimal priceAtNight,
        boolean available,
        AddressShortResponseDTO address
) {
}
