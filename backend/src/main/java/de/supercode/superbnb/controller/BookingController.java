package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.BookingRequestDTO;
import de.supercode.superbnb.dtos.BookingResponseDTO;
import de.supercode.superbnb.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //GET /api/bookings: Liste aller Buchungen anzeigen (nur für Administratoren)


    //POST /api/bookings: Eine Ferienwohnung buchen
    @PostMapping
    public ResponseEntity<BookingResponseDTO> propertyBooking(@RequestBody BookingRequestDTO bookingRequest) {
        return ResponseEntity.ok(bookingService.propertyBooking(bookingRequest));
    }


    //DELETE /api/bookings/{id}: Eine Buchung stornieren


}
