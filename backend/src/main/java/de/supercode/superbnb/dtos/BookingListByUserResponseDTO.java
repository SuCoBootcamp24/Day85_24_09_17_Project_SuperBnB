package de.supercode.superbnb.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingListByUserResponseDTO(
        long id,
        int guests,
        String propertyName,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        BigDecimal totalPrice
) {
}
