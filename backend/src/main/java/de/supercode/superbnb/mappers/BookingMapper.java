package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.booking.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.entities.person.Booking;
import de.supercode.superbnb.entities.Property;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public List<BookingListByUserResponseDTO> toDTOList(List<Booking> bookingList) {
        return bookingList.stream()
                .map(booking -> {
                    BigDecimal price = booking.getProperty().getPriceAtNight();
                    return new BookingListByUserResponseDTO(
                            booking.getId(),
                            booking.getGuests(),
                            booking.getProperty().getName(),
                            booking.getCheckInDate(),
                            booking.getCheckOutDate(),
                            price.multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate())))
                    );
                })
                .collect(Collectors.toList());
    }

    public PropertyListResponseDTO toPropertyListResponseDTO(Property property) {
        // Konvertierung der Property-Entität zu DTO (hier solltest du deine eigene Logik verwenden)
        return new PropertyListResponseDTO(
                property.getId(),
                property.getName(),
                property.getDescription(),
                property.getGuestsCapacity(),
                property.getPriceAtNight(),
                property.isAvailable(),
                new AddressShortResponseDTO(property.getAddress().getCity(),
                                            property.getAddress().getZipCode(),
                                            property.getAddress().getCountry())// falls Address als DTO vorliegt
        );
    }



}

