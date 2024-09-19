package de.supercode.superbnb.dtos;

import java.time.LocalDate;

public record BookingRequestDTO(

        long userId, //wird durch Cookies später ersetzt
        long propertyId,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {
}
