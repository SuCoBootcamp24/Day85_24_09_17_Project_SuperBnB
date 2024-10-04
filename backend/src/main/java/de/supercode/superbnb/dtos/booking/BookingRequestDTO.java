package de.supercode.superbnb.dtos.booking;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record BookingRequestDTO(

        @NotBlank
        int guests,

        @NotBlank
        long propertyId,
        @NotBlank
        LocalDate checkInDate,
        @NotBlank
        LocalDate checkOutDate
) {
}
