package de.supercode.superbnb.dtos.payment;

import java.time.YearMonth;

public record PaymentResponseDTO(

        String cardNumber,
        String cvv,
        YearMonth expirationDate
) {
}
