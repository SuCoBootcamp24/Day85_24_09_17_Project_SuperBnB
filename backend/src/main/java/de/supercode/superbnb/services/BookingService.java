package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.booking.BookingAdminListDTO;
import de.supercode.superbnb.dtos.booking.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.booking.BookingRequestDTO;
import de.supercode.superbnb.dtos.booking.BookingResponseDTO;
import de.supercode.superbnb.entities.Booking;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.mappers.BookingMapper;
import de.supercode.superbnb.repositorys.BookingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private AuthentificationService authentificationService;

    BookingRepository bookingRepository;
    UserService userService;
    PropertyService propertyService;

    BookingMapper bookingMapper;


    public BookingService(AuthentificationService authentificationService, BookingRepository bookingRepository, UserService userService, PropertyService propertyService, BookingMapper bookingMapper) {
        this.authentificationService = authentificationService;
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.propertyService = propertyService;
        this.bookingMapper = bookingMapper;
    }

    public boolean deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }


    public BookingResponseDTO propertyBooking(BookingRequestDTO bookingRequest, Authentication authentication) {
        // Derzeit stumpfe buchung ohne große überprüfung
        User user = userService.getUserByEmail(authentication.getName());
        Property property = propertyService.getPropertyById(bookingRequest.propertyId());
        if (!property.isAvailable()) throw new IllegalStateException("Property is not available");

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


    public List<BookingListByUserResponseDTO> getUserBookings(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        List<Booking> bookingList = bookingRepository.findAllByUserId(user.getId());
        return bookingMapper.toDTOList(bookingList);
    }

    public List<BookingAdminListDTO> getAllBookings(Authentication authentication) {
        if (!authentificationService.hasAdminRights(authentication)) return null;

        List<User> userList = userService.getAllUsers();

        return userList.stream()
                .map(user -> {
                    List<Booking> bookingList = bookingRepository.findAllByUserId(user.getId());

                    return new BookingAdminListDTO(
                            user.getFirstName(),
                            user.getLastName(),

                            bookingList.stream()
                                    .map(booking -> new BookingListByUserResponseDTO(
                                                booking.getId(),
                                                booking.getGuests(),
                                                booking.getProperty().getName(),
                                                booking.getCheckInDate(),
                                                booking.getCheckOutDate(),
                                                booking.getProperty().getPriceAtNight().multiply(
                                                        BigDecimal.valueOf(ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate())))
                                        )
                                    ).collect(Collectors.toList()),

                            bookingList.stream()
                                    .map(booking -> {
                                        BigDecimal nights = BigDecimal.valueOf(
                                            ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate())
                                        );
                                        return booking.getProperty().getPriceAtNight().multiply(nights);
                                    })
                                    .reduce(BigDecimal.ZERO, BigDecimal::add) // Summiere alle Preise
                            );

                })
                .collect(Collectors.toList());

    }

}
