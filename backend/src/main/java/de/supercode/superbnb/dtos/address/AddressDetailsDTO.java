package de.supercode.superbnb.dtos.address;

public record AddressDetailsDTO(

        long id,

        String street,
        String houseNumber,
        String postalCode,
        String city,
        String country
) {
}
