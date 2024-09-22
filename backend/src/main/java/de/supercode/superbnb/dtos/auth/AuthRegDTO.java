package de.supercode.superbnb.dtos.auth;

import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;

public record AuthRegDTO(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String email,

        @NotBlank
        String password,
        String phone,

        //---- address
        String street,
        String houseNumber,
        String postalCode,
        String city,
        String country,

        //---- payment

        @NotBlank
        String cardNumber,
        @NotBlank
        String cvv,

        @NotBlank
        YearMonth expirationDate

) {
}
