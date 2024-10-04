package de.supercode.superbnb.dtos.properties;


import java.math.BigDecimal;

public record PropertyUpdateDTO(

        String name,

        String description,

        Integer guestsCapacity,

        Integer rooms,

        Integer beds,

        Integer bathrooms,

        Float rating,

        BigDecimal priceAtNight,

        Boolean available,

        String street,

        String houseNumber,

        String zipCode,

        String city,

        String country
) {
}
