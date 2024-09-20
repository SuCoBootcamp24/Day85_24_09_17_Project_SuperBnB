package de.supercode.superbnb.dtos.booking;

import java.time.LocalDate;

public record BookingRequestDTO(

        long userId, //wird durch Cookies später ersetzt
        int guests,
        long propertyId,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {
}
