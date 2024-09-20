package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.booking.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.booking.BookingRequestDTO;
import de.supercode.superbnb.dtos.booking.BookingResponseDTO;
import de.supercode.superbnb.entities.Booking;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.mappers.BookingMapper;
import de.supercode.superbnb.repositorys.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    BookingRepository bookingRepository;
    UserService userService;
    PropertyService propertyService;

    BookingMapper bookingMapper;

    public boolean deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }


    public BookingResponseDTO propertyBooking(BookingRequestDTO bookingRequest) {
        // Derzeit stumpfe buchung ohne große überprüfung
        User user = userService.getUserById(bookingRequest.userId());
        Property property = propertyService.getPropertyById(bookingRequest.propertyId());

        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setProperty(property);
        newBooking.setCheckInDate(bookingRequest.checkInDate());
        newBooking.setCheckOutDate(bookingRequest.checkOutDate());
        newBooking.setGuests(bookingRequest.guests());
        newBooking = bookingRepository.save(newBooking);

        return new BookingResponseDTO(newBooking.getId(), newBooking.getGuests(), newBooking.getUser().getId(), newBooking.getProperty().getId(),
                newBooking.getBookingDate(), newBooking.getCheckInDate(), newBooking.getCheckOutDate());
    }


    public List<BookingListByUserResponseDTO> getUserBookings(Long id) {
        User user = userService.getUserById(id);
        List<Booking> bookingList = bookingRepository.findAllByUserId(user.getId());
        return bookingMapper.toDTOList(bookingList);
    }
}
