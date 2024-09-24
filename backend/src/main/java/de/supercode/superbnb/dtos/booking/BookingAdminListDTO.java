package de.supercode.superbnb.dtos.booking;

import java.math.BigDecimal;
import java.util.List;

public record BookingAdminListDTO(

        String firstName,

        String lastName,

        List<BookingListByUserResponseDTO> userBookings,

        BigDecimal userTotalBookings
) {
}
