package de.supercode.superbnb.dtos.favorite;

import java.math.BigDecimal;

public record FavoriteListDTO(

        long propertyId,
        String name,
        String description,

        BigDecimal pricePerNight
) {

}
