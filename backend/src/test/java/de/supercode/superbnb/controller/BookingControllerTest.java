package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.booking.BookingAdminListDTO;
import de.supercode.superbnb.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController MookBookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    void getAllBookings_AsAdmin_ShouldReturnBookings() {
        // Arrange
        List<BookingAdminListDTO> expectedBookings = List.of(
                new BookingAdminListDTO("John", "Doe", List.of(), BigDecimal.valueOf(1000))
        );
        when(bookingService.getAllBookings(any())).thenReturn(expectedBookings);

        // Act
        ResponseEntity<List<BookingAdminListDTO>> response = MookBookingController.getAllBookings(null);

        // Assert
        assertEquals(ResponseEntity.ok(expectedBookings), response);
        verify(bookingService, times(1)).getAllBookings(any());
    }

    @Test
    void propertyBooking() {
    }

    @Test
    void getUserBookings() {
    }

    @Test
    void cancelBooking() {
    }
}