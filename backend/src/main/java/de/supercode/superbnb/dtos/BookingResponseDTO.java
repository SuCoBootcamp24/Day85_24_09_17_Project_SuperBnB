package de.supercode.superbnb.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingResponseDTO(
        long id,
        int guests,
        Long userid,
        long propertyId,
        LocalDateTime bookingDate,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {
}
