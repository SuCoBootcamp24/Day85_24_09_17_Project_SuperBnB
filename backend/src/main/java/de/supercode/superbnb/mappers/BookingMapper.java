package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.BookingResponseDTO;
import de.supercode.superbnb.entities.Booking;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
                            price.multiply(BigDecimal.valueOf(booking.getGuests()))
                    );
                })
                .collect(Collectors.toList());
    }
}
