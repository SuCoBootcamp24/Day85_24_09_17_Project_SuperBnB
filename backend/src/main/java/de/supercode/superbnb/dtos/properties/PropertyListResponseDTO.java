package de.supercode.superbnb.dtos.properties;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;

import java.math.BigDecimal;

public record PropertyListResponseDTO(
        Long id,
        String title,
        String description,
        BigDecimal priceAtNight,
        boolean available,
        AddressShortResponseDTO address
) {
}
