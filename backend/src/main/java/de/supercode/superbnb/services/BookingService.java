package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.BookingRequestDTO;
import de.supercode.superbnb.dtos.BookingResponseDTO;
import de.supercode.superbnb.entities.Booking;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    BookingRepository bookingRepository;
    UserService userService;

    PropertyService propertyService;

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
        newBooking = bookingRepository.save(newBooking);

        return new BookingResponseDTO(newBooking.getId(), newBooking.getUser().getId(), newBooking.getProperty().getId(),
                newBooking.getBookingDate(), newBooking.getCheckInDate(), newBooking.getCheckOutDate());
    }


    public List<BookingResponseDTO> getUserBookings(Long id) {
        User user = userService.getUserById(id);
        return bookingRepository.findAllByUserId(user.getId());
    }
}
