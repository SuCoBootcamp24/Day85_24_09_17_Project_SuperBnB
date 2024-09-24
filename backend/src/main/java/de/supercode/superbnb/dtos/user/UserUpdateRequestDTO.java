package de.supercode.superbnb.dtos.user;

import de.supercode.superbnb.entities.person.Role;
import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;

public record UserUpdateRequestDTO(

        @NotBlank
        long id,

        String firstName,

        String lastName,

        String email,

        String password,

        String phone,

        //--- address

        String street,

        String houseNumber,

        String city,

        String zipCode,

        String country,

        //--- payment

        String cardNumber,

        String cvv,

        YearMonth expirationDate,

        //--- role
        Role role
) {
}
